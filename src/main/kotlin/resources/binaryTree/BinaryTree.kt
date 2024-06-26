package resources.binaryTree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Node(
    var data: Int,
    var right: Node? = null,
    var left: Node? = null
)

class BinaryTree(
    var head: Node? = null,
    var inOrderList: SnapshotStateList<Int> = mutableStateListOf(),
    var preOrderList: SnapshotStateList<Int> = mutableStateListOf(),
    var postOrderList: SnapshotStateList<Int> = mutableStateListOf(),
) {

    fun isEmpty(): Boolean {
        return head == null
    }

    fun insert(value: Int) {
        head = insertHelper(head, value)
        updateTraversalLists()
    }

    fun search(value: Int): Boolean {
        if (isEmpty()) {
            println("A árvore está vazia")
            return false
        } else{
            return searchHelper(head, value)
        }
    }

    fun remove(value: Int) {
        if (isEmpty()) {
            println("A árvore está vazia")
        } else if(search(value)) {
            if (head?.left == null && head?.right == null){
                head = null
                updateTraversalLists()
            } else {
                removeHelper(head, value)
                updateTraversalLists()
            }
        } else {
            println("Valor não está na lista")
        }
    }

    private fun insertHelper(node: Node?, value: Int): Node? {
        if (node == null) {
            return Node(value)
        } else if (node.data == value) {
            return node
        } else if (value < node.data) {
            node.left = insertHelper(node.left, value)
        } else {
            node.right = insertHelper(node.right, value)
        }
        return node
    }

    private fun searchHelper(node: Node?, value: Int): Boolean {
        if (node == null) {
            return false
        }
        if(value == node.data) {
            return true
        } else if(value < node.data){
            return searchHelper(node.left, value)
        } else {
            return searchHelper(node.right, value)
        }
    }

    private fun removeHelper(node: Node?, value: Int): Node? {
        if (node == null) {
            return node
        } else if(value < node.data) {
            node.left = removeHelper(node.left, value)
        } else if(value > node.data) {
            node.right = removeHelper(node.right, value)
        } else {
            if(node.left == null && node.right == null) {
                updateTraversalLists()
                return null
            } else if(node.right != null) {
                node.data = successor(node)
                node.right = removeHelper(node.right, node.data)
            } else {
                node.data = predecessor(node)
                node.left = removeHelper(node.left, node.data)

            }
        }
        return node
    }

    private fun successor(node: Node): Int {
        var root = node.right
        while (root?.left != null) {
            root = root.left
        }
        return root?.data ?: node.data
    }

    private fun predecessor(node: Node): Int {
        var root = node.left
        while (root?.right != null) {
            root = root.right
        }
        return root?.data ?: node.data
    }

    private fun preOrderHelper(node: Node?) {
        if (node != null) {
            preOrderList.add(node.data)
            preOrderHelper(node.left)
            preOrderHelper(node.right)
        }
    }

    private fun inOrderHelper(node: Node?) {
        if (node != null) {
            inOrderHelper(node.left)
            inOrderList.add(node.data)
            inOrderHelper(node.right)
        }
    }

    private fun postOrderHelper(node: Node?) {
        if(node != null) {
            postOrderHelper(node.left)
            postOrderHelper(node.right)
            postOrderList.add(node.data)
        }
    }

    private fun updateTraversalLists() {
        inOrderList.clear()
        preOrderList.clear()
        postOrderList.clear()

        inOrderHelper(head)
        preOrderHelper(head)
        postOrderHelper(head)
    }

}
