const canvas = document.getElementById('graphCanvas');
const ctx = canvas.getContext('2d');
const addNodeButton = document.getElementById('addNode');
const addEdgeButton = document.getElementById('addEdge');
const graphTypeSelect = document.getElementById('graphType');
const messagesDiv = document.getElementById('messages');
const adjacencyMatrixDiv = document.getElementById('adjacencyMatrix');
const isomorphismResultDiv = document.getElementById('isomorphismResult');
const graphCompleteResultDiv = document.getElementById('graphCompleteResult');

let nodes = [];
let edges = [];
let adjacencyMatrix = [];

class Node {
    constructor(x, y, id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
}

class Edge {
    constructor(node1, node2, color) {
        this.node1 = node1;
        this.node2 = node2;
        this.color = color;
    }
}

canvas.addEventListener('click', (e) => {
    const rect = canvas.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;
    if (addNodeMode) {
        addNode(x, y);
        addNodeMode = false;
    } else if (addEdgeMode) {
        if (selectedNode == null) {
            selectedNode = getNodeAt(x, y);
            if (!selectedNode) {
                displayMessage("Nenhum nó selecionado.");
            } else {
                displayMessage(`Nó ${selectedNode.id} selecionado. Selecione outro nó para criar uma aresta.`);
            }
        } else {
            const targetNode = getNodeAt(x, y);
            if (targetNode) {
                if (graphTypeSelect.value === 'multigraph' || !edgeExists(selectedNode, targetNode)) {
                    const color = getRandomColor();
                    addEdge(selectedNode, targetNode, color);
                    displayMessage("Aresta adicionada.");
                } else {
                    displayMessage("Aresta já existe entre esses nós.");
                }
            } else {
                displayMessage("Nenhum nó selecionado.");
            }
            selectedNode = null;
            addEdgeMode = false;
        }
    }
    drawGraph();
    updateAdjacencyMatrix();
    updateAdjacencyMatrixDisplay();
});

let addNodeMode = false;
let addEdgeMode = false;
let selectedNode = null;

addNodeButton.addEventListener('click', () => {
    addNodeMode = true;
    displayMessage("Clique no canvas para adicionar um nó.");
});

addEdgeButton.addEventListener('click', () => {
    addEdgeMode = true;
    selectedNode = null; // Reset selectedNode when starting to add a new edge
    displayMessage("Clique em dois nós para adicionar uma aresta.");
});

function addNode(x, y) {
    const node = new Node(x, y, nodes.length);
    nodes.push(node);
    displayMessage(`Nó ${node.id} adicionado.`);
}

function addEdge(node1, node2, color) {
    const edge = new Edge(node1, node2, color);
    edges.push(edge);
}

function getNodeAt(x, y) {
    return nodes.find(node => Math.hypot(node.x - x, node.y - y) < 10);
}

function edgeExists(node1, node2) {
    // Modified to ensure checking edges both ways for simple graph
    return edges.some(edge => (edge.node1 === node1 && edge.node2 === node2) || (edge.node1 === node2 && edge.node2 === node1));
}

function drawGraph() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    edges.forEach((edge, index) => {
        ctx.beginPath();
        ctx.strokeStyle = edge.color;
        const offset = index * 2; // Offset to space out the edges
        ctx.moveTo(edge.node1.x + offset, edge.node1.y + offset);
        ctx.lineTo(edge.node2.x + offset, edge.node2.y + offset);
        ctx.stroke();
    });
    nodes.forEach(node => {
        ctx.beginPath();
        ctx.arc(node.x, node.y, 10, 0, 2 * Math.PI);
        ctx.fill();
        ctx.strokeText(node.id, node.x - 3, node.y + 3);
    });
}

function displayMessage(msg) {
    messagesDiv.textContent = msg;
}

function getRandomColor() {
    const letras = '0123456789ABCDEF';
    let cor = '#';
    for (let i = 0; i < 6; i++) {
        cor += letras[Math.floor(Math.random() * 16)];
    }
    return cor;
}

// Matriz de Adjacência

function initializeAdjacencyMatrix() {
    adjacencyMatrix = new Array(nodes.length);
    for (let i = 0; i < adjacencyMatrix.length; i++) {
        adjacencyMatrix[i] = new Array(nodes.length).fill(0);
    }
}

function updateAdjacencyMatrix() {
    initializeAdjacencyMatrix();
    edges.forEach(edge => {
        const { node1, node2 } = edge;
        adjacencyMatrix[node1.id][node2.id] = 1;
        adjacencyMatrix[node2.id][node1.id] = 1; // Para grafos não direcionados
    });
}

function updateAdjacencyMatrixDisplay() {
    let matrixHtml = '<h3>Matriz de Adjacência</h3><table>';
    matrixHtml += '<tr><td></td>'; // Cabeçalho vazio para ajustar a matriz
    nodes.forEach(node => {
        matrixHtml += `<td>${node.id}</td>`;
    });
    matrixHtml += '</tr>';
    adjacencyMatrix.forEach((row, index) => {
        matrixHtml += '<tr>';
        matrixHtml += `<td>${index}</td>`;
        row.forEach(cell => {
            matrixHtml += `<td>${cell}</td>`;
        });
        matrixHtml += '</tr>';
    });
    matrixHtml += '</table>';
    adjacencyMatrixDiv.innerHTML = matrixHtml;
}

// Verificação de Isomorfismo

function isomorfico(graph1, graph2) {
    // Verifica se os grafos têm o mesmo número de nós e arestas
    if (graph1.nodes.length !== graph2.nodes.length || graph1.edges.length !== graph2.edges.length) {
        return false;
    }

    // Implementar algoritmo para verificar isomorfismo (exemplo simplificado)
    // Aqui, poderíamos usar propriedades como grau dos nós, estruturas de vizinhança, etc.
    // Neste exemplo, apenas retornaremos um resultado simplificado
    return true; // Implementação simplificada para o exemplo
}

// Verificação de Grafo Completo

function grafoCompleto() {
    const totalNodes = nodes.length;
    const maxEdges = totalNodes * (totalNodes - 1) / 2; // Número máximo de arestas em um grafo completo

    return edges.length === maxEdges;
}

// Atualizar resultados de Isomorfismo e Completo

if (grafoCompleto()) {
    graphCompleteResultDiv.textContent = "O grafo é completo.";
} else {
    graphCompleteResultDiv.textContent = "O grafo não é completo.";
}

// Inicialização e Atualização da Matriz de Adjacência

initializeAdjacencyMatrix();
updateAdjacencyMatrix();
updateAdjacencyMatrixDisplay();
