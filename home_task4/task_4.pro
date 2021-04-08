phoneBookRecord("Аршинов", "89307118137", address("Москва", "Полбина", "16", "5")).
phoneBookRecord("Аршинов", "89304118137", address("Новотроицк", "Толстого", "16", "5")).
phoneBookRecord("Самсонова", "893071181457", address("Владимир", "Малютина", "12", "3")).
car("Аршинов", "Audi", "Silver", 1800000).
car("Самсонова", "Renault", "White", 800000).
bankAccount("Аршинов", "Москва", "Alfabank", "345678").
bankAccount("Аршинов", "Новотроицк", "Cбер", "35678").
bankAccount("Самсонова", "Владимир", "Binbank", "3543678").
findCarByPhone(Phone, Make, Color, Price) :- phoneBookRecord(Surename, Phone, _), car(Surename, Make, Color, Price).
findCarMakeByPhone(Phone, Make) :- findCarByPhone(Phone, Make, _, _).
findDeposit(Surename, City, Address, Bank, Phone) :- phoneBookRecord(Surename, Phone, address(City, _, _, _)),
                                                     phoneBookRecord(Surename, Phone, Address),
                                                     bankAccount(Surename, City, Bank, _).