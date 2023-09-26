package arvorebin;

class ArvoreBin {
    No raiz;

    public ArvoreBin() {
        raiz = null;
    }

 
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

 
    private No inserirRec(No raiz, int valor) {
        if (raiz == null) {
            raiz = new No(valor);
            return raiz;
        }

        if (valor >= raiz.valor) {
            raiz.direita = inserirRec(raiz.direita, valor);
        } else {
            raiz.esquerda = inserirRec(raiz.esquerda, valor);
        }

        return raiz;
    }
    
    public void percorrerPreOrdem() {
        percorrerPreOrdemRec(raiz);
    }

    private void percorrerPreOrdemRec(No no) {
        if (no != null) {
            System.out.print(no.valor + " "); // Visita o nó atual
            percorrerPreOrdemRec(no.esquerda); // Percorre a subárvore esquerda
            percorrerPreOrdemRec(no.direita); // Percorre a subárvore direita
        }
    }

    // Método para percorrer a árvore em ordem (in-ordem)
    public void percorrerInOrdem() {
        percorrerInOrdemRec(raiz);
    }

    private void percorrerInOrdemRec(No no) {
        if (no != null) {
            percorrerInOrdemRec(no.esquerda); // Percorre a subárvore esquerda
            System.out.print(no.valor + " "); // Visita o nó atual
            percorrerInOrdemRec(no.direita); // Percorre a subárvore direita
        }
    }

    // Método para percorrer a árvore em pós-ordem
    public void percorrerPosOrdem() {
        percorrerPosOrdemRec(raiz);
    }

    private void percorrerPosOrdemRec(No no) {
        if (no != null) {
            percorrerPosOrdemRec(no.esquerda); // Percorre a subárvore esquerda
            percorrerPosOrdemRec(no.direita); // Percorre a subárvore direita
            System.out.print(no.valor + " "); // Visita o nó atual
        }
    }
    
    public void removerMaiorElemento() {
        raiz = removerMaiorElementoRec(raiz);
    }

    private No removerMaiorElementoRec(No raiz) {
        // Se a árvore estiver vazia, não há nada a fazer
        if (raiz == null) {
            return raiz;
        }

        // Se a árvore não for vazia, vamos para o nó mais à direita
        if (raiz.direita != null) {
            raiz.direita = removerMaiorElementoRec(raiz.direita);
            return raiz;
        }

        // Se chegamos aqui, o nó atual é o maior elemento
        return raiz.esquerda;
    }
    
    public void removerMenorElemento() {
        raiz = removerMenorElementoRec(raiz);
    }

    private No removerMenorElementoRec(No raiz) {
        // Se a árvore estiver vazia, não há nada a fazer
        if (raiz == null) {
            return raiz;
        }

        // Se a árvore não for vazia, vamos para o nó mais à esquerda
        if (raiz.esquerda != null) {
            raiz.esquerda = removerMenorElementoRec(raiz.esquerda);
            return raiz;
        }

        // Se chegamos aqui, o nó atual é o menor elemento
        return raiz.direita;
    }
    
    public void removerElemento(int valor) {
        raiz = removerElementoRec(raiz, valor);
    }

    private No removerElementoRec(No raiz, int valor) {
        // Caso base: Se a árvore estiver vazia ou o valor não foi encontrado
        if (raiz == null) {
            return raiz;
        }

        // Se o valor a ser removido for menor do que o valor do nó atual, vá para a esquerda
        if (valor < raiz.valor) {
            raiz.esquerda = removerElementoRec(raiz.esquerda, valor);
        }
        // Se o valor a ser removido for maior do que o valor do nó atual, vá para a direita
        else if (valor > raiz.valor) {
            raiz.direita = removerElementoRec(raiz.direita, valor);
        }
        // Se o valor a ser removido é igual ao valor do nó atual, este é o nó a ser removido
        else {
            // Casos de remoção:
            // a) Nó folha ou com apenas um filho
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }

            // b) Nó com dois filhos: Encontrar o maior valor na subárvore esquerda
            raiz.valor = encontrarMaiorValor(raiz.esquerda);

            // Remover o maior valor encontrado na subárvore esquerda
            raiz.esquerda = removerElementoRec(raiz.esquerda, raiz.valor);
        }
        
        return raiz;
    }

    // Método para encontrar o maior valor em uma subárvore
    private int encontrarMaiorValor(No raiz) {
        int maiorValor = raiz.valor;
        while (raiz.direita != null) {
            maiorValor = raiz.direita.valor;
            raiz = raiz.direita;
        }
        return maiorValor;
    }
    
    public static void main(String[] args) {
        ArvoreBin arvore = new ArvoreBin();
        ArvoreBin arvoreSemRep = new ArvoreBin();

     
        arvore.inserir(14);
        arvore.inserir(15);
        arvore.inserir(4);
        arvore.inserir(9);
        arvore.inserir(7);
        arvore.inserir(18);
        arvore.inserir(3);
        arvore.inserir(5);
        arvore.inserir(16);
        arvore.inserir(4);
        arvore.inserir(20);
        arvore.inserir(17);
        arvore.inserir(9);
        arvore.inserir(14);
        arvore.inserir(5);
        
        System.out.println("\nPercorrer em Pre-Ordem:");
        arvore.percorrerPreOrdem();

        // Percorrer a árvore em ordem (in-ordem)
        System.out.println("\nPercorrer em In-Ordem:");
        arvore.percorrerInOrdem();

        // Percorrer a árvore em pós-ordem
        System.out.println("\nPercorrer em Pos-Ordem:");
        arvore.percorrerPosOrdem();
        
        arvore.removerMaiorElemento();
         
        System.out.println("\nPercorrer em Pre-Ordem, eliminando o MAIOR elemento");
        arvore.percorrerPreOrdem();
        
        arvore.removerMenorElemento();
        
        System.out.println("\nPercorrer em Pre-Ordem, eliminando o MENOR elemento");
        arvore.percorrerPreOrdem();
        
        arvoreSemRep.inserir(14);
        arvoreSemRep.inserir(15);
        arvoreSemRep.inserir(4);
        arvoreSemRep.inserir(9);
        arvoreSemRep.inserir(7);
        arvoreSemRep.inserir(18);
        arvoreSemRep.inserir(3);
        arvoreSemRep.inserir(5);
        arvoreSemRep.inserir(16);
        arvoreSemRep.inserir(20);
        arvoreSemRep.inserir(17);
        
        arvoreSemRep.removerElemento(9);
        
        System.out.println("\nPercorrer em Pre-Ordem, eliminando o elemento");
        arvoreSemRep.percorrerPreOrdem();
    }
}