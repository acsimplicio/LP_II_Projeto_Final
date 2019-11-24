mr = input()

mr = mr.split(" ")

if len(mr) == 2:

    quem = input()

    mergulhadores = range(1, int(mr[0]) + 1)
    retornados = quem.split(" ")

    resultado = ""

    for m in mergulhadores:
        if not str(m) in retornados:
            resultado = resultado + str(m) + " "

    if resultado == "":
        resultado = "*"

    print (resultado)
else:
    print ("Prencha as duas informacoes")