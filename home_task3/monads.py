from pymonad.io import IO
from typing import Callable, TypeVar

A = TypeVar('A') # pylint: disable=invalid-name

def read_input():
    return input()