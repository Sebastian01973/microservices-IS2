from bson import DBRef
from bson.objectid import ObjectId
from typing import get_args, Generic, TypeVar
import database.database as dbase

# Variable para el tipo de dato generico
T = TypeVar('T')


class RepositoryInterface(Generic[T]):

    # constructor de la clase
    def __init__(self):
        self.db = dbase.connect_to_db()
        theClass = get_args(self.__orig_bases__[0])
        self.collection = theClass[0].__name__.lower()

    # Trae el valor buscado
    def get_values_DBRef_from_list(self, theList, object):
        newList = []
        theCollection = self.db[object["_id"].collection]
        for item in theList:
            value = theCollection.find_one({"_id": ObjectId(item.id)})
            value["_id"] = str(value["_id"])
            newList.append(value)
        return newList

    # Trae el valor buscado
    def get_values_DBRef(self, x):
        keys = x.keys()
        for k in keys:
            if isinstance(x[k], DBRef):
                theCollection = self.db[x[k].collection]
                value = theCollection.find_one({"_id": ObjectId(x[k].id)})
                value["_id"] = value["_id"].__str__()
                x[k] = value
                x[k] = self.get_values_DBRef(x[k])
            elif isinstance(x[k], list) and len(x[k]) > 0:
                # x[k] = self.get_values_DBRef_from_list(x[k], x)
                x[k] = x[k]
            elif isinstance(x[k], dict):
                x[k] = self.get_values_DBRef(x[k])
        return x

    # De una lista devuelve una lista
    def format_list(self, x):
        newList = []
        for item in x:
            if isinstance(item, ObjectId):
                newList.append(str(item))
        if len(newList) == 0:
            newList = x
        return newList

    def transform_object_ids(self, x):
        for attribute in x.keys():
            if isinstance(x[attribute], ObjectId):
                x[attribute] = str(x[attribute])
            elif isinstance(x[attribute], list):
                x[attribute] = self.format_list(x[attribute])
            elif isinstance(x[attribute], dict):
                x[attribute] = self.transform_object_ids(x[attribute])
        return x

    def find_all(self):
        theCollection = self.db[self.collection]
        data = []
        for x in theCollection.find():
            x["_id"] = str(x["_id"])
            x = self.transform_object_ids(x)
            x = self.get_values_DBRef(x)
            data.append(x)
        return data

    # Busca la coleccion
    def find_by_id(self, id):
        theCollection = self.db[self.collection]
        x = theCollection.find_one({"_id": ObjectId(id)})
        if x == None:
            x = {}
        else:
            x = self.get_values_DBRef(x)
            x["_id"] = str(x["_id"])
        return x

    # Funcion para actualizar
    def update(self, id, item: T):
        theCollection = self.db[self.collection]
        # delattr(item, "_id")
        item = item.__dict__
        updateItem = {"$set": item}
        x = theCollection.update_one({"_id": ObjectId(id)}, updateItem)
        return {"updated_count": x.matched_count}

    # Funcion para eliminar
    def delete(self, id):
        theCollection = self.db[self.collection]
        account = theCollection.delete_one({"_id": ObjectId(id)}).deleted_count
        return {"deleted_count": account}

    # Funcioon que retorna el objectID DBREF
    def object_to_DBRef(self, item: T):
        nameCollection = item.__class__.__name__.lower()
        return DBRef(nameCollection, ObjectId(item._id))

    # Funcion que transforma una Ref
    def transform_Refs(self, item):
        theDict = item.__dict__
        keys = list(theDict.keys())
        for k in keys:
            if theDict[k].__str__().count("object") == 1:
                newObject = self.object_to_DBRef(getattr(item, k))
                setattr(item, k, newObject)
        return item

    # Funcion para guardar
    def save(self, item: T):
        thecollection = self.db[self.collection]
        theId = ""
        item = self.transform_Refs(item)
        if hasattr(item, "_id") and item._id != "":
            theId = item._id
            _id = ObjectId(theId)
            thecollection = self.db[self.collection]
            delattr(item, "_id")
            item = item.__dict__
            updateItem = {"$set": item}
            x = thecollection.update_one({"_id": _id}, updateItem)
        else:
            _id = thecollection.insert_one(item.__dict__)
            theId = str(_id.inserted_id)
        x = thecollection.find_one({"_id": ObjectId(theId)})
        x["_id"] = str(x["_id"])
        return self.find_by_id(theId)

    # Funcion de query
    def query(self, theQuery):
        thecollection = self.db[self.collection]
        data = []
        for x in thecollection.find(theQuery):
            x["_id"] = x["_id"].__str__()
            x = self.transform_object_ids(x)
            x = self.get_values_DBRef(x)
            data.append(x)
        return data

    # Funcion de query con agregacion
    def query_aggregation(self, theQuery):
        theCollection = self.db[self.collection]
        data = []
        for x in theCollection.aggregate(theQuery):
            x["_id"] = x["_id"].__str__()
            x = self.transform_object_ids(x)
            x = self.get_values_DBRef(x)
            data.append(x)
        return data
