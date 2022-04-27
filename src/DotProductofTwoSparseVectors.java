import java.util.HashMap;
import java.util.Map;

/**
 * 1570. Dot Product of Two Sparse Vectors
 * <p>
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 */

class SparseVector {
    // O(nums.length) time.
// O(numNonZeroValues) space.

    Map<Integer, Integer> map;

    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                map.put(i, nums[i]);       // index-value map
        }
    }

    // O(min(vec1numNonZeroValues, vec2numNonZeroValues)) time because we iterate through non-zero values of the vector that has fewer non-zero values and for each value we check in O(1) time whether the other vector has a non-zero value at that index.
    // O(1) space.
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        if (this.map.size() < vec.map.size()) return vec.dotProduct(this);

        int dotProduct = 0;

        for (Integer key : this.map.keySet()) {
            if (vec.map.containsKey(key)) {
                dotProduct += vec.map.get(key) * this.map.get(key);
            }
        }

        return dotProduct;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);