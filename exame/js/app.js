// Simulação de Estruturas de Dados

// Pilha
let pilha = [];

function empilhar() {
    const valor = document.getElementById('stackInput').value;
    if (valor) {
        pilha.push(valor);
        document.getElementById('stackInput').value = '';
        atualizarPilha();
    } else {
        alert("Por favor, digite um valor para empilhar.");
    }
}

function desempilhar() {
    if (pilha.length > 0) {
        pilha.pop();
        atualizarPilha();
    } else {
        alert("A pilha está vazia.");
    }
}

function atualizarPilha() {
    document.getElementById('stackDisplay').innerText = pilha.join('\n');
}

// Lista Ligada
class No {
    constructor(valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

class ListaLigada {
    constructor() {
        this.cabeca = null;
    }

    inserir(valor, posicao) {
        const novoNo = new No(valor);
        if (posicao === 0) {
            novoNo.proximo = this.cabeca;
            this.cabeca = novoNo;
        } else {
            let atual = this.cabeca;
            let anterior = null;
            let indice = 0;
            while (indice < posicao && atual !== null) {
                anterior = atual;
                atual = atual.proximo;
                indice++;
            }
            if (anterior) {
                novoNo.proximo = atual; // Conecta o novo nó ao nó atual da posição
                anterior.proximo = novoNo; // Conecta o nó anterior ao novo nó
            } else {
                alert("Posição inválida.");
            }
        }
    }

    remover(posicao) {
        if (posicao === 0 && this.cabeca) {
            this.cabeca = this.cabeca.proximo;
        } else {
            let atual = this.cabeca;
            let anterior = null;
            let indice = 0;
            while (indice < posicao && atual !== null) {
                anterior = atual;
                atual = atual.proximo;
                indice++;
            }
            if (anterior && atual) {
                anterior.proximo = atual.proximo;
            } else {
                alert("Posição inválida.");
            }
        }
    }

    mostrar() {
        let atual = this.cabeca;
        let resultado = [];
        while (atual !== null) {
            resultado.push(atual.valor);
            atual = atual.proximo;
        }
        return resultado.join(' -> ');
    }
}

let listaLigada = new ListaLigada();

function inserirLista() {
    const valor = document.getElementById('listInput').value;
    const posicao = parseInt(document.getElementById('listPosition').value);
    if (valor && !isNaN(posicao)) {
        listaLigada.inserir(valor, posicao);
        document.getElementById('listInput').value = '';
        document.getElementById('listPosition').value = '';
        atualizarLista();
    } else {
        alert("Por favor, digite um valor e uma posição válida.");
    }
}

function removerLista() {
    const posicao = parseInt(document.getElementById('listPosition').value);
    if (!isNaN(posicao)) {
        listaLigada.remover(posicao);
        document.getElementById('listPosition').value = '';
        atualizarLista();
    } else {
        alert("Por favor, digite uma posição válida.");
    }
}

function atualizarLista() {
    document.getElementById('listDisplay').innerText = listaLigada.mostrar();
}

// Grafo
class Grafo {
    constructor() {
        this.vertices = {};
    }

    adicionarVertice(vertice) {
        if (!this.vertices[vertice]) {
            this.vertices[vertice] = [];
        } else {
            alert("O vértice já existe.");
        }
    }

    adicionarAresta(v1, v2) {
        if (this.vertices[v1] && this.vertices[v2]) {
            this.vertices[v1].push(v2);
            this.vertices[v2].push(v1);
        } else {
            alert("Um ou ambos os vértices não existem.");
        }
    }

    mostrar() {
        let resultado = [];
        for (let vertice in this.vertices) {
            resultado.push(`${vertice}: ${this.vertices[vertice].join(', ')}`);
        }
        return resultado.join('\n');
    }
}

let grafo = new Grafo();

function adicionarVertice() {
    const vertice = document.getElementById('vertexInput').value;
    if (vertice) {
        grafo.adicionarVertice(vertice);
        document.getElementById('vertexInput').value = '';
        atualizarGrafo();
    } else {
        alert("Por favor, digite um vértice.");
    }
}

function adicionarAresta() {
    const v1 = document.getElementById('edgeV1Input').value;
    const v2 = document.getElementById('edgeV2Input').value;
    if (v1 && v2) {
        grafo.adicionarAresta(v1, v2);
        document.getElementById('edgeV1Input').value = '';
        document.getElementById('edgeV2Input').value = '';
        atualizarGrafo();
    } else {
        alert("Por favor, digite ambos os vértices.");
    }
}

function atualizarGrafo() {
    document.getElementById('graphDisplay').innerText = grafo.mostrar();
}
