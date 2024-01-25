def MostrarProdutos(listaDeProdutos):
    print("\n\033[1;33;44m PRODUTOS: \033[m")
    for i in range(len(listaDeProdutos)):
        if listaDeProdutos[i][0] < 10:
            print(f"\033[1;33;44m    {listaDeProdutos[i][0]}  {listaDeProdutos[i][1]:.<29}", end = "")
        else:
            print(f"\033[1;33;44m    {listaDeProdutos[i][0]} {listaDeProdutos[i][1]:.<29}", end = "")
        if listaDeProdutos[i][2] < 100:
            print(f"..{listaDeProdutos[i][2]:.2f} \033[m")
        elif listaDeProdutos[i][2] < 1000:
            print(f".{listaDeProdutos[i][2]:.2f} \033[m")
        else:
            print(f"{listaDeProdutos[i][2]:.2f} \033[m")

def MostrarEspecificações(Especificações, listaDeProdutos):
    print("\n\033[1;33;44m Digite o número do produto para ver as especificações. \033[m"
          "\n\033[1;33;44m Digite 0 para terminar. \033[m\n")
    while True:
        try:
            desejo = int(input("\nnúmero produto: "))
            if desejo == 0:
                break
            elif desejo < 0:
                print("Apenas números maiores que 0\n")
            else:
                for i in range(len(listaDeProdutos)):
                    if desejo == listaDeProdutos[i][0]:
                        print(f"\n\033[1;33;44m Especificação de {listaDeProdutos[i][1]}: \033[m")
                        print(f"\033[1;33;44m     {Especificações[i]} \033[m")
                        break
                else:
                    print("Produto não encontrado.\n")
        except:
            print("digite apenas números, e apenas inteiros: \n")

def AdicionarItem(carrinho, listaDeProdutos):
    MostrarProdutos(listaDeProdutos)
    print("\n\033[1;33;44m Digite o número do produto que deseja adicionar. \033[m"
          "\n\033[1;33;44m Digite 0 para terminar. \033[m\n")
    while True:
        try:
            desejo = int(input("Qual produto deseja? "))
            if desejo == 0:
                break
            elif desejo < 0:
                print("Apenas números maiores que 0\n")
            else:
                for i in range(len(listaDeProdutos)):
                    if desejo == listaDeProdutos[i][0]:
                        carrinho.append(listaDeProdutos[i])
                        print(f"{listaDeProdutos[i][1]} Adicionado(a) ao carrinho!\n")
                        break
                else:
                    print("Produto não encontrado.\n")
        except:
            print("digite apenas números\n")

def RemoverItem(carrinho):
    MostrandoCompra(carrinho)
    if len(carrinho) == 0:
        print("\n\033[1;33;44m O carrinho está vazio. \033[m")
    else:    
        print("\n\033[1;33;44m Digite o número do produto que deseja remover. \033[m"
            "\n\033[1;33;44m Digite 0 para terminar. \033[m\n")
        while True:
            try:
                desejo = int(input("Qual produto deseja remover? "))
                if desejo == 0:
                    break
                elif desejo < 0:
                    print("Apenas números maiores que 0\n")
                else:
                    for i in range(len(carrinho)):
                        if desejo == carrinho[i][0]:
                            print(f"{carrinho[i][1]} Removido(a) do carrinho!\n")
                            carrinho.remove(carrinho[i])
                            break
                    else:
                        print("Produto não encontrado.\n")
            except:
                print("digite apenas números\n")

def MostrandoCompra(carrinho):
    total = 0
    carrinho.sort()
    print(f"\033[1;33;44m Seu carrinho: \033[m")
    for i in range(len(carrinho)):
        total += carrinho[i][2]
        if carrinho[i][0] < 10:
            print(f"\033[1;33;44m    {carrinho[i][0]}  {carrinho[i][1]:.<29}", end = "")
        else:
            print(f"\033[1;33;44m    {carrinho[i][0]} {carrinho[i][1]:.<29}", end = "")
        if carrinho[i][2] < 100:
            print(f"..{carrinho[i][2]:.2f} \033[m")
        elif carrinho[i][2] < 1000:
            print(f".{carrinho[i][2]:.2f} \033[m")
        else:
            print(f"{carrinho[i][2]:.2f} \033[m")
    print(f"\033[1;33;44m    {'O valor total é:':.<32}", end = "")
    if total < 100:
        print(f"..{total:.2f} \033[m")
    elif total < 1000:
        print(f".{total:.2f} \033[m")
    else:
        print(f"{total:.2f} \033[m")

def FinalizarCompra(CEP, carrinho, nome, email):
    if len(carrinho) == 1:
        print(f"Obrigado por comprar na Adrian's & Adrian's {nome},"
        f"\nQualquer novidade, enviaremos para o email: {email}."
        f"\nO produto chegará no CEP {str(CEP)[:1]}.{str(CEP)[2:5]}-{str(CEP)[-3:]} em até 30 dias."
        "\nAgradecemos sua visita.")
    else:
        print(f"Obrigado por comprar na Adrian's & Adrian's {nome},"
        f"\nQualquer novidade, enviaremos para o email: {email}."
        f"\nOs produtos chegaram no CEP {str(CEP)[:1]}.{str(CEP)[2:5]}-{str(CEP)[-3:]} em até 30 dias."
        "\nAgradecemos sua visita.")
