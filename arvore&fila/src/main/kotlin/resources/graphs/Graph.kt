package resources.graphs

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList


class Graph(
    var nodes: SnapshotStateList<String> = mutableStateListOf(),
    var edges: SnapshotStateList<Pair<String, String>> = mutableStateListOf(),
) {
    var matrix: SnapshotStateList<MutableList<Int>> = mutableStateListOf()

    fun addNode(data: String) {
        nodes.add(data)
        for (row in matrix) {
            row.add(0)
        }
        matrix.add(MutableList(nodes.size) { 0 })
    }

    fun removeNode(node: String) {
        val index = nodes.indexOf(node)
        if (index != -1) {
            nodes.removeAt(index)
            matrix.removeAt(index)
            for (row in matrix) {
                row.removeAt(index)
            }
            getEdges()
        }
    }

    fun addEdge(src: String, dst: String) {
        if (nodes.indexOf(src) == -1 || nodes.indexOf(dst) == -1) {
            print("Endereços inválidos")
        } else {
            val index1 = nodes.indexOf(src)
            val index2 = nodes.indexOf(dst)
            matrix[index1][index2] = 1
            getEdges()
        }
    }

    fun removeEdge(src: String, dst: String) {
        if (nodes.indexOf(src) == -1 || nodes.indexOf(dst) == -1) {
            print("Endereços inválidos")
        } else {
            val index1 = nodes.indexOf(src)
            val index2 = nodes.indexOf(dst)
            matrix[index1][index2] = 0
            getEdges()
        }
    }


    fun getNodes(): List<String> {
        return nodes.toList()
    }

    fun getEdges() {
        edges.clear()
        for (i in nodes.indices) {
            for (j in nodes.indices) {
                if (matrix[i][j] == 1) {
                    edges.add(nodes[i] to nodes[j])
                }
            }
        }
    }

    fun printGraph() {
        print("   ")
        for (node in nodes) {
            print("$node  ")
        }
        println()
        for (i in matrix.indices) {
            print("${nodes[i]}  ")
            for (j in matrix[i].indices) {
                print("${matrix[i][j]}  ")
            }
            println()
        }
    }
}




fun main() {
    val g = Graph()
    g.addNode('A'.toString())
    g.addNode('B'.toString())
    g.addNode('C'.toString())
    g.addNode('P'.toString())
    g.addEdge('A'.toString(), 'B'.toString())
    g.addEdge('B'.toString(), 'C'.toString())
    g.printGraph()
    g.removeEdge('A'.toString(), 'B'.toString())
    g.printGraph()

}