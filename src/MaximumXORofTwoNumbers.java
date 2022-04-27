import java.util.HashSet;

/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * <p>
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * <p>
 * Could you do this in O(n) runtime?
 */

public class MaximumXORofTwoNumbers {
    public static int findMaximumXOR(int[] nums) {

        int max = 0;
        int mask = 0;
        for (int i = 31; i >= 0; i--) {
            // 从最高位试着找nums的前缀
            mask = mask | (1 << i);
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(mask & num);
            }
            //判断最大异或结果的当前位是否为1
            //用temp为当前试探的异或最大值（即我们先假设当前位为1），temp=max|(1左移i)
            int temp = max | (1 << i);

            // 假设a，b属于set集合，只要满足temp=a^b，则说明temp的第i位可以为1，
            // 将max=temp，否则，temp的第i位不可以为1.然后进入下一个循环， 直到判断max的最后一位停止
            for (int prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 10, 5, 25, 2, 8};

        System.out.println(findMaximumXOR(nums1));   // Output: 28  Explanation: The maximum result is 5 ^ 25 = 28.
    }
}

/**
 * 建树+贪心策略
 */
//class Solution {
//    public:
//    struct TreeNode {
//        int val;
//        TreeNode *left;
//        TreeNode *right;
//        TreeNode(int x) : val(x), left(NULL), right(NULL) {}
//    };
//    int findMaximumXOR(vector<int>& nums) {
//        TreeNode *root = new TreeNode(-1);
//        int maxRes = 0;
//        //建立二叉树
//        for (auto num : nums){
//            TreeNode *treePtr = root;
//            //按照[31,30, .... 1，0]二进制数字串的位位0、1进行区别，1放左边，0放右边
//            //从高位向低位逐渐检测，递归寻找放的位置
//            for (int i = 31; i >= 0; --i){
//                if ((num & (1 << i)) == 0){
//                    //num的第i位为0，需要放到当前节点的left
//                    if (treePtr->left == NULL){
//                        treePtr->left = new TreeNode(0);
//                    }
//                    treePtr = treePtr->left;//转移到left，进行i + 1的放置
//                }
//                else{
//                    //num的第i位为1，需要放到当前节点的right
//                    if (treePtr->right == NULL){
//                        treePtr->right = new TreeNode(1);
//                    }
//                    treePtr = treePtr->right;//转移到right，进行i + 1的放置
//                }
//            }
//            //最后放在底部节点的left位置
//            treePtr->left = new TreeNode(num);
//        }
//        //搜索
//        for (auto num : nums){
//            TreeNode *treePtr = root;
//            //从高位向低位寻找，贪心策略，尽量保持当前位异或为1
//            for (int i = 31; i >= 0; --i){
//                if ((num & (1 << i)) == 0){
//                    //如果num的第i位0，则应该优先选择right，因为right为1，这样1 ^ 0 == 1
//                    if (treePtr->right != NULL){
//                        treePtr = treePtr->right;
//                    }
//                    else{
//                        treePtr = treePtr->left;
//                    }
//                }
//                else{
//                    //如果此位是1，则应该优先选择left
//                    if (treePtr->left != NULL){
//                        treePtr = treePtr->left;
//                    }
//                    else{
//                        treePtr = treePtr->right;
//                    }
//                }
//            }
//            //最后去底端的left进行异或操作
//            maxRes = max(maxRes, treePtr->left->val ^ num);
//        }
//        return maxRes;
//    }
//};

