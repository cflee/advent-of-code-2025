class UnionFind(capacity: Int) {
    private val parent = IntArray(capacity) { index -> index }
    private val height = IntArray(capacity) { 1 }
    private val size = IntArray(capacity) { 1 }

    fun find(i: Int): Int {
        if (parent[i] != i) {
            parent[i] = find(parent[i])
        }
        return parent[i]
    }

    fun union(i: Int, j: Int): Boolean {
        val iParent = find(i)
        val jParent = find(j)
        if (iParent == jParent) return false
        if (height[iParent] <= height[jParent]) {
            parent[iParent] = jParent
            size[jParent] += size[iParent]
            if (height[iParent] == height[jParent]) height[jParent]++
        } else {
            parent[jParent] = iParent
            size[iParent] += size[jParent]
        }
        return true
    }

    fun size(i: Int): Int {
        return size[find(i)]
    }
}
