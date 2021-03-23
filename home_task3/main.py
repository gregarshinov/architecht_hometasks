from pymonad.io import IO


main = IO().then(lambda x: x + 5).then(print)


if __name__ == '__main__':
    main.run()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
