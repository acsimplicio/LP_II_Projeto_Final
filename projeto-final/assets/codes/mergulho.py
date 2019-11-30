mr = input()

mr = mr.split(" ")

if len(mr) == 2:

    quem = input()

    mergulhadores = range(1, int(mr[0]) + 1)
    retornados = quem.split(" ")

    resultadoList = []
    resultado = ""

    for m in mergulhadores:
        if not str(m) in retornados:
            resultadoList.append(str(m))
    resultado = " ".join(resultadoList)

    if resultado == "":
        resultado = "*"

    print (resultado)
else:
    print ("Prencha as duas informacoes")