from pymonad.io import IO
from pymonad.maybe import Maybe, Just, Nothing
from pymonad.state import State
from pymonad.list import ListMonad
from pyrsistent import v, pdeque
import re


def test():
    return "(1 + 2) * 4 + 3 / -5"


def tokenize(raw_expression: Just) -> Maybe:
    return Just(v(*re.findall(r"-?[0-9]+|[+\-*/]|[()]", raw_expression)))

def is_space(ch: Just):
    return ch == Just(' ')


def is_digit(str_in_question: Just) -> Just:
    try:
        float(str_in_question)
        return Just(True)
    except ValueError:
        return Nothing


def is_opening_parenthesis(ch: Just) -> Just:
    return Just(ch == Just('('))


def is_closing_parenthesis(ch: Just):
    return Just(ch == ')')


def is_operator(ch: Just):
    return Just(ch in (Just('+'), Just('/'), Just('*'), Just('-')))


def get_precedence(op):
    precedence = {
        '(': 3,
        ')': 3,
        '*': 2,
        '/': 2,
        '+': 1,
        '-': 1
    }
    return Just(precedence[op])


def traverse_tokens(token: Just, state: Just) -> Just:

    if is_digit(state):
        resulting_sequence = put(resulting_sequence, token)
        traverse_tokens()
    elif is_opening_parenthesis(token):
        append(stack, token)
    elif is_operator(token):
        if len(stack):
            stack_top = peep(stack)
            if stack_top:
                while get_precedence(stack_top) >= get_precedence(token) and not is_opening_parenthesis(stack_top):
                    resulting_sequence = put(resulting_sequence, pop(stack))
                    stack_top = peep(stack)
                    if not stack_top:
                        break
        append(stack, token)
    elif is_closing_parenthesis(token):
        stack_top = peep(stack)
        while not is_opening_parenthesis(stack_top):
            resulting_sequence = put(resulting_sequence, pop(stack))
            stack_top = peep(stack)
        pop(stack)

def process_token():
    pass

def convert_to_reverse_polish_notation(infix_expression: Just) -> Maybe:
    return infix_expression.bind(traverse_tokens)


main = Just("(1 + 2) * 4 + 3 / -5")\
        .bind(tokenize)\
        .bind(convert_to_reverse_polish_notation)


if __name__ == '__main__':
    print(main.maybe('', lambda x: x))
