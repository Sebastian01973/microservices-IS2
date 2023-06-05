from enum import Enum


class BovineType(Enum):
    BULL = 'BULL'
    COW = 'COW'
    CALF = 'CALF'
    VEAL = 'VEAL'


def get_gender(gender):
    match gender.upper():
        case BovineType.BULL.value:
            return BovineType.BULL.value.upper()
        case BovineType.COW.value:
            return BovineType.COW.value.upper()
        case BovineType.CALF.value:
            return BovineType.CALF.value.upper()
        case BovineType.VEAL.value:
            return BovineType.VEAL.value.upper()
        case _:
            return None
