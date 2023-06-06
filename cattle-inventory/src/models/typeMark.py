from enum import Enum


class TypeMark(Enum):
    TATOO = 'TATOO'
    IRON = 'IRON'
    EARMUFF = 'EARMUFF'
    LEATHER_PUNCH = 'LEATHER PUNCH'
    IDENTIFIERS = 'IDENTIFIERS'


def get_type_mark(type_mark):
    match type_mark.upper():
        case TypeMark.TATOO.value:
            return TypeMark.TATOO.value
        case TypeMark.IRON.value:
            return TypeMark.IRON.value
        case TypeMark.EARMUFF.value:
            return TypeMark.EARMUFF.value
        case TypeMark.LEATHER_PUNCH.value:
            return TypeMark.LEATHER_PUNCH.value
        case TypeMark.IDENTIFIERS.value:
            return TypeMark.IDENTIFIERS.value
        case _:
            return None
