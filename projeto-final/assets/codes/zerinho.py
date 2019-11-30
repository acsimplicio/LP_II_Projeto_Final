def get_vencedor(index):
    if index == 0:
        return "A"
    elif index == 1:
        return "B"
    elif index == 2:
        return "C"

numeros_texto = input()

numeros = numeros_texto.split(" ")

n_0 = numeros.count("0")
n_1 = numeros.count("1")

if n_0 == 1:
    print (get_vencedor(numeros.index("0")))
elif n_1 == 1:
    print (get_vencedor(numeros.index("1")))
else:
    print ("*")

        
