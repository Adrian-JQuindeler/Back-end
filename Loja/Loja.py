import Funções
#Criando a lista com os produtos vendidos
listaDeProdutos = [
    [1, "COOLER + PROCESSADOR + SSD", 999.99],
    [2, "PLACA-MÃE + MEMÓRIA RAM", 815.20],
    [3, "MEMÓRIA RAM", 314.25],
    [4, "PROCESSADOR", 729.99],
    [5, "MOUSE PAD", 34.46],
    [6, "PLACA-MÃE", 621.81],
    [7, "GABINETE", 135.46],
    [8, "MONITOR", 761.47],
    [9, "COOLER", 55.99],
    [10, "FONTE", 98.25],
    [11, "MOUSE", 115.50],
    [12, "SSD", 249.00]]

Especificações = ["Pacote especial: Cooler + Processador AMD Ryzen 7 5700G 3.8GHz + SSD Kingston SA400S37 480GB",
    "Pacote especial: Placa-Mãe + Memória RAM 16GB",
    "Memória RAM 16gb",
    "Processador Intel Core i9-13900K, 24-Core, \n     32-Threads, 3.0GHz (5.8GHz Turbo)",
    "Mouse pad para jogos RGB, 800 x 300 mm / 31,5 × 11,8 \n     polegadas",
    "Placa-Mãe Asus Prime A520M-E. ",
    "Gabinete TGT G250, Lateral Acrilico, \n     TGT-G250PR-01",
    "Monitor Gamer Mancer Horizon Z PRO, 27 Pol. VA, \n     FHD, 1ms, 165Hz, FreeSync, HDMI/DP",
    "Cooler Master líquido de CPU MasterLiquid \n     ML360 Illusion Close-Loop AIO",
    "Fonte Gamer GP650 80 Plus Bronze PFC Ativo 650W",
    "Mouse Gamer Sem Fio Logitech G703 LIGHTSPEED \n     com RGB LIGHTSYNC, 6 Botões Programáveis",
    "SSD Kingston NV2 1TB NVMe M.2 2280"]

#Mostrando o nome da empresa
print("\033[1;30;44m===================================\033[m")
print("\033[1;30;44m     Adrian's & Adrian's LTDA.     \033[m")
print("\033[1;30;44m===================================\033[m")

carrinho = []

nome = input("Informe seu nome: ")
email = input("Seu email: ")
print(f"\nSeja bem vindo, {nome}")

Funções.MostrarProdutos(listaDeProdutos)
while True:
    while True:
        try:
            caminho = int(input("\nO que deseja fazer?"
                            "\n[1] Mostrar especificações dos produtos"
                            "\n[2] Adicionar item ao carrinho"
                            "\n[3] Remover item do carrinho"
                            "\n[4] Mostrar carrinho"
                            "\n[5] Finalizar compra"
                            "\nSua resposta: "))
            break
        except:
            print("Por favor, digite apenas números, e apenas inteiros: ")

    if caminho == 1:
        #Mostrando especificações dos produtos
        Funções.MostrarEspecificações(Especificações, listaDeProdutos)
        
    elif caminho == 2:
        #Colocando produtos no carrinho do cliente
        Funções.AdicionarItem(carrinho, listaDeProdutos)
    
    elif caminho == 3:
        #Removendo produtos no carrinho do cliente
        Funções.RemoverItem(carrinho)    

    elif caminho == 4:
        #Mostrando o carrinho do cliente
        Funções.MostrandoCompra(carrinho)
    
    elif caminho == 5:
        if len(carrinho) == 0:
            print("\n\034[1;33;44m Nenhum item foi selecionado. \034[m")
            break
        else:
            while True:
                #Terminando a venda
                Funções.MostrandoCompra(carrinho)
                resposta = input("Deseja finalizar a compra? [S/N] ").lower()
                if resposta[0] == "s":
                  while True:
                        cep = input("Informe seu CEP: ")
                        if not cep.isdigit():
                            print("O CEP precisa ter 8 dígitos!")
                        elif len(str(cep)) != 8:
                            print("Digite apenas números")
                        else:
                          break
                  # Conclusão e despedida
                  Funções.FinalizarCompra(cep, carrinho, nome, email)
                  break
                elif resposta == 'n':
                    print("Compra cancelada.")
                    break
                else:
                    print("Resposta inválida\n")
        break
    else:
        print("Resposta inválida!")
print("\033[1;30;44m Volte Sempre! \033[m")