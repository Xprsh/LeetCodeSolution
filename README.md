## 这是我的 LeetCode 题解集，希望能对你有所帮助
###### 最后更新于：2020年9月30日

# LeetCode

### 2. 两数相加

给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```



**个人思考思路：**

一开始考虑将两链表转成对应整数后相加，后发现会溢出，随考虑在链表位上相加，后思考失败，查看解题思路



**思路：**

将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 0，比如 987 + 23 = 987 + 023 = 1010
每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1
小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head。**使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。**



**代码：**

```java
public class Q2_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;

        while (l1 != null || l2 != null) {
            // 判定该位是否为空，是则代表0
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + carry;

            carry = sum / 10;
            cur.next = new ListNode(sum % 10);

            // 移动当前位指针
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // 循环结束后判断首位是否需要进位
        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return pre.next;
    }
}
```



https://leetcode-cn.com/problems/add-two-numbers/solution/hua-jie-suan-fa-2-liang-shu-xiang-jia-by-guanpengc/



### 25. k 个一组反转链表

给你一个链表，每 *k* 个节点一组进行翻转，请你返回翻转后的链表。

*k* 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 *k* 的整数倍，那么请将最后剩余的节点保持原有顺序。

 

**示例：**

给你这个链表：`1->2->3->4->5`

当 *k* = 2 时，应当返回: `2->1->4->3->5`

当 *k* = 3 时，应当返回: `3->2->1->4->5`

 

**说明：**

- 你的算法只能使用常数的额外空间。
- **你不能只是单纯的改变节点内部的值**，而是需要实际进行节点交换。



**个人思考思路**

利用栈每存储k个节点就进行一次翻转，其中需要注意的是：

1. 第一次翻转时需要改变头节点为新反转之后的
2. 从第二次翻转开始需要将cur的前一节点指向新翻转后的翻转范围的第一个的元素



思路比较乱，且代码效率不高，通过后参考他人代码



**思路：**

<img src="https://pic.leetcode-cn.com/f63d5ca4d3f055ce8e4591c8bc51c288791f88da9ccec9617bc8bb51c26163a2.png" style="zoom:50%;" />

1、找到待翻转的k个节点（注意：若剩余数量小于 k 的话，则不需要反转，因此直接返回待翻转部分的头结点即可）。
2、对其进行翻转。并返回翻转后的头结点（注意：翻转为左闭又开区间，所以本轮操作的尾结点其实就是下一轮操作的头结点）。
3、对下一轮 k 个节点也进行翻转操作。
4、将上一轮翻转后的尾结点指向下一轮翻转后的头节点，即将每一轮翻转的k的节点连接起来。



**代码：**

```java
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
    左闭右开区间
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
```



### 138. 复制带随机指针的链表

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 **[深拷贝](https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin)**。 

我们用一个由 `n` 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 `[val, random_index]` 表示：

- `val`：一个表示 `Node.val` 的整数。
- `random_index`：随机指针指向的节点索引（范围从 `0` 到 `n-1`）；如果不指向任何节点，则为 `null` 。



**个人思路：**

使用哈希表存储新旧 Node ，通过两次遍历分别完成链表和随机指针的复制

**思路：**

第一种方式与我的实现类似，第二种方式比较有意思，没有借助哈希表，采用三步走的方式：

这题的最大难点就在于**复制随机指针**，比如下图中

- 节点**1**的随机指针指向节点**3**
- 节点**3**的随机指针指向节点**2**
- 节点**2**的随机指针指向空
  ![4.jpg](https://pic.leetcode-cn.com/f7a10394dd43e2cb26185277b07f9c1fb64b4578e34549b6c73c186140d52afe-4.jpg)

我们可以用三步走来搞定这个问题
**第一步**，根据遍历到的原节点创建对应的新节点，每个新创建的节点是在原节点后面，比如下图中原节点**1**不再指向原原节点**2**，而是指向新节点**1**
![5.jpg](https://pic.leetcode-cn.com/360dbd3b89c25324287f4cef2c22ba8a20e946891ac887f70703b211893aafa0-5.jpg)

**第二步**是最关键的一步，用来设置新链表的随机指针
![6.jpg](https://pic.leetcode-cn.com/b531fb496fd478a2db6ba7bc805cda08b825771817dd24cdd616946a89800fbb-6.jpg)
上图中，我们可以观察到这么一个规律

- 原节点1的随机指针指向原节点3，新节点1的随机指针指向的是原节点3的next
- 原节点3的随机指针指向原节点2，新节点3的随机指针指向的是原节点2的next

也就是，原节点`i`的随机指针(如果有的话)，指向的是原节点`j`
那么新节点`i`的随机指针，指向的是原节点`j`的**next**

**第三步**就简单了，只要将两个链表分离开，再返回新链表就可以了
![7.jpg](https://pic.leetcode-cn.com/9b5c6e99aa89284c8a7b423bc36fec7af39fac3f8bb709e77483e574e02ef1cd-7.jpg)

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) {
            return null;
        }
        Node p = head;
        //第一步，在每个原节点后面创建一个新节点
        //1->1'->2->2'->3->3'
        while(p!=null) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        p = head;
        //第二步，设置新节点的随机节点
        while(p!=null) {
            if(p.random!=null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        Node dummy = new Node(-1);
        p = head;
        Node cur = dummy;
        //第三步，将两个链表分离
        while(p!=null) {
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummy.next;
    }
}	
```

https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/



### Q206. 翻转链表

反转一个单链表。

**个人解题思路：**

这题比较简单，我采用了迭代，利用两个指针完成

**思路：**

除了上述方法，还可以利用外部空间，把结点遍历到容器中，再使用自身 API 翻转

还有一种比较骚的方法，就是使用递归

递归也有两种方式：

第一种思路与迭代方式一致，代码如下：

```java
class Solution {
    ListNode pre = null, tmp = null;
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return pre;
        tmp = head.next;
        head.next = pre;
        pre = head;
        head = tmp;
        return reverseList(head);
    }
}
```

第二种就比较不好理解了

递归的两个条件：

1. 终止条件是当前节点或者下一个节点==null
2. 在函数内部，改变节点的指向，也就是 head 的下一个节点指向 head 递归函数那句

```
head.next.next = head
```

很不好理解，其实就是 head 的下一个节点指向head。
递归函数中每次返回的 cur 其实只最后一个节点，在递归函数内部，改变的是当前节点的指向。
动画演示如下：
![递归.gif](https://pic.leetcode-cn.com/dacd1bf55dec5c8b38d0904f26e472e2024fc8bee4ea46e3aa676f340ba1eb9d-%E9%80%92%E5%BD%92.gif)



**代码：**

```java
class Solution {
	public ListNode reverseList(ListNode head) {
		//递归终止条件是当前为空，或者下一个节点为空
		if(head==null || head.next==null) {
			return head;
		}
		//这里的cur就是最后一个节点
		ListNode cur = reverseList(head.next);
		//这里请配合动画演示理解
		//如果链表是 1->2->3->4->5，那么此时的cur就是5
		//而head是4，head的下一个是5，下下一个是空
		//所以head.next.next 就是5->4
		head.next.next = head;
		//防止链表循环，需要将head.next设置为空
		head.next = null;
		//每层递归函数都返回cur，也就是最后一个节点
		return cur;
	}
}
```



https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/



### Q3. 无重复字符的最长字串

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

例如：

输入："dvdf"

输出："3"



**个人思路：**

采用滑动窗口算法，但是忘记怎么做了，太菜了



**思路：**

1. 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
2. 我们定义不重复子串的开始位置为 start，结束位置为 end
3. 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
4. 无论是否更新 start，都会更新其 map 数据结构和结果 ans。



**代码：**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
```



### Q11. 盛最多水的容器

给你 *n* 个非负整数 *a*1，*a*2，...，*a*n，每个数代表坐标中的一个点 (*i*, *ai*) 。在坐标内画 *n* 条垂直线，垂直线 *i* 的两个端点分别为 (*i*, *ai*) 和 (*i*, 0)。找出其中的两条线，使得它们与 *x* 轴共同构成的容器可以容纳最多的水。

**说明：**你不能倾斜容器，且 *n* 的值至少为 2。

 

<img src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" alt="img" style="zoom:50%;" />

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。



**个人思路：**

我使出了两个for，很强！



**思路：**

本题是一道经典的面试题，最优的做法是使用「双指针」。如果读者第一次看到这题，不一定能想出双指针的做法。

双指针代表的是 可以作为容器边界的所有位置的范围。在一开始，双指针指向数组的左右边界，表示 数组中所有的位置都可以作为容器的边界，因为我们还没有进行过任何尝试。在这之后，我们每次将 对应的数字较小的那个指针 往 另一个指针 的方向移动一个位置，就表示我们认为 这个指针不可能再作为容器的边界了。

**代码：**

```java
class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int maxArea = 0;

        while (start != end) {
            maxArea = Math.max(maxArea,
                    Math.min(height[start], height[end]) * (end - start));
            if (height[start] <= height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return maxArea;
    }
}
```



### Q15. 三数之和

给你一个包含 *n* 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 *a，b，c ，*使得 *a + b + c =* 0 ？请你找出所有满足条件且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。



**个人思路：**

a+b+c = 0 可以变为 a + b = -c，从而在 O(n<sup>2</sup>) 时间内完成，但是超时

**思路：**

![](https://s1.ax1x.com/2020/08/07/af8XUU.png)



**代码：**

```
public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
        return new ArrayList<>();
    }

    List<List<Integer>> ans = new ArrayList<>();

    // 元素从小到大排序
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
        // 去重,跟以前的比，不是以后的
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }

        // nums[i] > 0，意味着数组中已无满足条件的数组
        if (nums[i] > 0) {
            break;
        }

        int l = i + 1;
        int r = nums.length - 1;

        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];
            if (sum == 0) {
                ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                while (l < r && nums[l] == nums[l + 1]) l++; // 去重
                while (l < r && nums[r] == nums[r - 1]) r--; // 去重
                l++;
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }
    }
    return ans;
}
```

https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/



### Q16. 最接近的三数之和

给定一个包括 *n* 个整数的数组 `nums` 和 一个目标值 `target`。找出 `nums` 中的三个整数，使得它们的和与 `target` 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

**个人思路：**

与上题类似，采用排序 + 双指针完成

- 再使用前指针指向 `start = i + 1` 处，后指针指向 `end = nums.length - 1` 处，也就是结尾处
- 根据 `sum = nums[i] + nums[start] + nums[end]` 的结果，判断 sum 与目标 target 的距离，如果更近则更新结果 ans
- 同时判断 sum 与 target 的大小关系，因为数组有序，如果 `sum > target` 则 `end--`，如果 `sum < target` 则 `start++`，如果 `sum == target` 则说明距离为 0 直接返回结果



**代码：**

```java
public int threeSumClosest(int[] nums, int target) {
    if (nums == null || nums.length < 3) {
        throw new IllegalArgumentException("Nums is Illegally!");
    }

    int ans = nums[0] + nums[1] + nums[2];

    if(nums.length == 3){
        return ans;
    }

    // 元素从小到大排序
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
        // 去重,跟以前的比，不是以后的
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }

        int l = i + 1;
        int r = nums.length - 1;

        while (l < r) {
            int sum = nums[i] + nums[l] + nums[r];

            // 找到值一样结果，直接返回target
            if (sum == target) {
                return target;
            }

            if (Math.abs(sum - target) < Math.abs(ans - target)) {
                ans = sum;
            }

            // 移动指针
            if (sum - target < 0) {
                l++;
            } else {
                r--;
            }
        }
    }
    return ans;

}
```



### Q26. 删除排序数组中的重复项

给定一个排序数组，你需要在**[ 原地](http://baike.baidu.com/item/原地算法)** 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组** 并在使用 O(1) 额外空间的条件下完成。



PS：你不需要考虑数组中超出新长度后面的元素。



**个人思路：**

采用双指针，个人实现代码较为繁杂，故贴上大佬代码

```java
 public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int p = 0;
    int q = 1;
    while(q < nums.length){
        if(nums[p] != nums[q]){
            nums[p + 1] = nums[q];
            p++;
        }
        q++;
    }
    return p + 1;
}
```

**优化：**

数组中没有重复元素，按照上面的方法，每次比较时 `nums[p]` 都不等于 `nums[q]`，因此就会将 `q` 指向的元素原地复制一遍，这个操作其实是不必要的。

因此我们可以添加一个小判断，当 `q - p > 1` 时，才进行复制。



### *Q42. 接雨水

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 **感谢 Marcos** 贡献此图。



**个人思路：**

按列计算可存储雨水，时间复杂度为O(n<sup>2</sup>)，虽然过了，但是效率太低



**思路：**

这道题有很多解法，其中动态规划是比较好的选择

我们注意到，解法二中。对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下。

首先用两个数组，`max_left [i] `代表第 `i` 列左边最高的墙的高度，`max_right[i]` 代表第 `i` 列右边最高的墙的高度。（一定要注意下，第 i 列左（右）边最高的墙，是不包括自身的，和 leetcode 上边的讲的有些不同）

对于 `max_left`我们其实可以这样求。

`max_left [i] = Max(max_left [i-1]`,`height[i-1])`。它前边的墙的左边的最高高度和它前边的墙的高度选一个较大的，就是当前列左边最高的墙了。

对于 max_right我们可以这样求。

`max_right[i] = Max(max_right[i+1]`,`height[i+1])` 。它后边的墙的右边的最高高度和它后边的墙的高度选一个较大的，就是当前列右边最高的墙了。

这样，我们再利用解法二的算法，就不用在 `for` 循环里每次重新遍历一次求 `max_left` 和 `max_right` 了。

- Java

```
public int trap(int[] height) {
    int sum = 0;
    int[] max_left = new int[height.length];
    int[] max_right = new int[height.length];
    
    for (int i = 1; i < height.length - 1; i++) {
        max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
    }
    for (int i = height.length - 2; i >= 0; i--) {
        max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
    }
    for (int i = 1; i < height.length - 1; i++) {
        int min = Math.min(max_left[i], max_right[i]);
        if (min > height[i]) {
            sum = sum + (min - height[i]);
        }
    }
    return sum;
}
```

时间复杂度：O*(*n)。

空间复杂度：O*(*n)，用来保存每一列左边最高的墙和右边最高的墙。

在此基础上，还能使用双指针优化空间占用

https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/



### *Q121. 买卖股票的最佳时机

给定一个数组，它的第 *i* 个元素是一支给定股票第 *i* 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。



**个人思路：**

知道采用动态规划，但是不知道怎么做，菜的一批



**思路：**

既然用了动态规划，肯定是要用**空间换时间**
我们先看下执行状态，这是一个买入和卖出的过程，而且能交易一次即只能买卖一次，所以**买入**之后，状态可以转换到**卖出**，但是**卖出**之后就不能再**买入**了，因为只能**买卖一次**
<img src="https://pic.leetcode-cn.com/25e14cb8588c4b28b334942a378973679cf4dc41341395bee9a9e7ea8052669e-2.jpg" alt="2.jpg" style="zoom:50%;" />

我们需要用开辟额外的空间，来不断更新买入和卖出的最大收益
我们先看一个最简单的例子，假设只数组长度是**1**，即只有一天
<img src="https://pic.leetcode-cn.com/3ba6f1ee2fbb3dc2239a316c6ae0af9132f22b851a05c035ae0dd1b9de63b8ba-3.jpg" alt="3.jpg" style="zoom:50%;"/>

这时候不管股价是多少，我的收益都是**0**，为什么呢？
因为必须要**先买后卖**，只有一天的情况下无法卖出，所以卖出的收益是0。
股价是7美元，也就是花了7美元买进这个股票，那么对应到第一天买入的最大收益就是-7美元。两者一合计当然是坐地不动更划算。

我们再来看一个复杂点的，假设有三天
这里**卖出**数组记录了每次卖出的最大收益，数组下标i即对应第i天的最大收益
**买入**数组记录了每次买入的最大收益，数组下标i即对应第i天的最大收益
第一天的时候还是老样子，卖出是0，买入是-7
<img src="https://pic.leetcode-cn.com/11b946e368f6e265b3d2b743e4a94754d07397e3dac0f4589ac96d9861723b6e-4.jpg" alt="4.jpg" style="zoom:50%;" />

第二天卖出的最大利润仍然是0
卖出的收益是：第一天买入的值-7，加上第二天股票价格，也就是第一天我花7美元买入(买入对应收益就是-7)，第二天1美元卖出，所以第二天的卖出收益就是-6美元。这当时是亏本了，于是继续不动。
买入的收益是：记录当天买入的最大收益，因为买入都是负的，所以最大收益就是找一个价格最低的，即前一天买入的最大收益 vs 第二天的股价，第一天花了7美元买入的，第二天股票跌到1美元，当然是买第二天的
<img src="https://pic.leetcode-cn.com/2066080bd8b8b7fd603f260b12b375fdd75ce699b98286c22c98ffcee48262ce-5.jpg" alt="5.jpg" style="zoom:50%;" />

第三天卖出的利润就变了
卖出收益是：第二天买入的值-1，加上第三天的股票价格，也就是前一天买入的最大收益 **-1**加上第三天的股票价格5，于是第三天买入的收益就变成了4
买入的收益是：第二天买入的值-1 vs 第三天买入的股价-5，选择不动
<img src="https://pic.leetcode-cn.com/f9b42e79f7f785b18c64785169049621c919bbdd331269ba286170be84baf781-6.jpg" alt="6.jpg" style="zoom:50%;" />

第i天卖出的最大收益计算公式为：

- i-1天卖出的最大收益 **vs** i-1天买入的最大收益+第i天的股价
- sell[i] = max(sell[i-1],buy[i-1]+prices[i])

第i天买入的最大收益计算公式为：(注意第i天前面有个负号)

- i-1天买入的最大收益 **vs** -第i天的股价
- buy[i] = max(buy[i-1],-prices[i])



**代码：**

```java
public int maxProfit(int[] prices) {
    if (prices == null || prices.length < 2) {
        return 0;
    }

    int sell[] = new int[prices.length];
    int buy[] = new int[prices.length];

    // 初始化第一天收益
    sell[0] = 0;
    buy[0] = -prices[0];

    for (int i = 1; i < prices.length; i++) {
        // 更新卖出最佳收益
        sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

        // 更新买入最佳收益
        buy[i] = Math.max(buy[i - 1], -prices[i]);
    }

    return Math.max(sell[prices.length - 1], buy[prices.length - 1]);

}
```

https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/wu-chong-shi-xian-xiang-xi-tu-jie-121-mai-mai-gu-p/



### Q209. 长度最小的子数组

给定一个含有 **n** 个正整数的数组和一个正整数 **s ，**找出该数组中满足其和 **≥ s** 的长度最小的 **连续** 子数组，并返回其长度**。**如果不存在符合条件的子数组，返回 0。

 **个人思路：**

采用双指针，类似于队列，我们把数组中的元素不停的入队，直到总和大于等于s为止，接着记录下队列中元素的个数，然后再不停的出队，直到队列中元素的和小于s为止（如果不小于s，也要记录下队列中元素的个数，这个个数其实就是不小于s的连续子数组长度，我们要记录最小的即可）。接着再把数组中的元素添加到队列中……重复上面的操作，直到数组中的元素全部使用完为止。

***代码：**

```java
public int minSubArrayLen(int s, int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }

    int ans = 0;
    int sum = nums[0];

    for (int start = 0, end = 0; end < nums.length; ) {

        if (sum >= s) {
            ans = ans == 0 ? end - start + 1 : Math.min(ans, end - start + 1);
            // 已找到最佳答案
            if (ans == 1) {
                return ans;
            }
            sum -= nums[start++];
        } else {
            if (end == nums.length - 1) {
                break;
            }
            sum += nums[++end];
        }

    }

    return ans;
}
```



**思路：**

除了双指针外，官方还提供了一个清奇的思路，**前缀和 + 二分查找**

我们申请一个临时数组sums，其中sums[i]表示的是原数组nums前i个元素的和，题中说了“给定一个含有 n 个**正整数**的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。我们只需要找到sums[k]-sums[j]>=s，那么k-j就是满足的连续子数组，**但不一定是最小的**，所以我们要继续找，直到找到最小的为止。怎么找呢，我们可以使用两个for循环来枚举，但这又和第一种暴力求解一样了，所以我们可以换种思路，求sums[k]-sums[j]>=s我们可以求sums[j]+s<=sums[k]，那这样就好办了，因为数组sums中的元素是递增的，也就是排序的，我们只需要求出sum[j]+s的值，然后使用二分法查找即可找到这个k。

```java
    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int[] sums = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= length; i++) {
            int target = s + sums[i];
            int index = Arrays.binarySearch(sums, target);
            if (index < 0)
                index = ~index;
            if (index <= length) {
                min = Math.min(min, index - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
```

注意这里的函数int index = Arrays.binarySearch(sums, target);如果找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值应该在数组中的位置
举个例子，比如排序数组[2，5，7，10，15，18，20]如果我们查找18，因为有这个数会返回18的下标5，如果我们查找9，因为没这个数会返回-4（至于这个是怎么得到的，大家可以看下源码，这里不再过多展开讨论），我们对他取反之后就是3，也就是说如果我们在数组中添加一个9，他在数组的下标是3，也就是第4个位置（也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标）



### Q141. 环形链表

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。



**个人思路：**

快慢指针或哈希表，评论区也有为链表赋值来进行判断的

下面以快慢指针为例：

**代码：**

```java
public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }

    ListNode fast = head;
    ListNode slow = head;

    // 快慢指针，快指针每次走两步，慢指针走一步
    while (true) {
        for (int i = 0; i < 2; i++) {
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            if (fast == null) {
                return false;
            }
        }
        slow = slow.next;
    }
}
```



### Q202. 快乐数

编写一个算法来判断一个数 `n` 是不是快乐数。

「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 **无限循环** 但始终变不到 1。如果 **可以变为** 1，那么这个数就是快乐数。

如果 `n` 是快乐数就返回 `True` ；不是，则返回 `False` 。

 

**个人思路：**

使用集合保存中间出现的整数，如果重复则代表不是快乐数，直至遇到1

**思路：**

除了上述方法，也可以利用快慢指针来判断是否出现重复的数



方法：使用“快慢指针”思想找出循环：“快指针”每次走两步，“慢指针”每次走一步，当二者相等时，即为一个循环周期。此时，判断是不是因为1引起的循环，是的话就是快乐数，否则不是快乐数。

注意：此题不建议用集合记录每次的计算结果来判断是否进入循环，因为这个集合可能大到无法存储；另外，也不建议使用递归，同理，如果递归层次较深，会直接导致调用栈崩溃。不要因为这个题目给出的整数是int型而投机取巧。

技巧：**判断循环就用快慢指针**

**代码：**

```java
class Solution {
public:
    int bitSquareSum(int n) {
        int sum = 0;
        while(n > 0)
        {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }
    

    bool isHappy(int n) {
        int slow = n, fast = n;
        do{
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }while(slow != fast);
        
        return slow == 1;
    }

};
```



### Q876. 链表的中间结点

给定一个带有头结点 `head` 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。



**个人思路：**

快慢指针，用两个指针 `slow` 与 `fast` 一起遍历链表。`slow` 一次走一步，`fast` 一次走两步。那么当 `fast` 到达链表的末尾时，`slow` 必然位于中间。

**代码：**

```java
public ListNode middleNode(ListNode head) {
    if(head == null || head.next == null){
        return head;
    }

    ListNode slow = head;
    ListNode fast = head.next;

    while (true){
        if(fast.next == null || fast.next.next == null){
            return slow.next;
        }else {
            slow = slow.next;
            fast = fast.next.next;
        }
    }
}
```



### *Q56. 合并区间

给出一个区间的集合，请合并所有重叠的区间。



**个人思路：**

采用暴力方式，太菜了



**思路：**

区间合并

1. 对起点和终点分别进行排序，将起点和终点一一对应形成一个数组。

- 如果没有overlap,返回当前起点和终点
- 如果有overlap,判断以下条件

2. 找出最小的起点

3. 如果当前终点大于下一个数组的起点的时候，比较当前终点和下一个终点的大小，取为right

4. 返回满足要求的区间[[left,right]]

**代码：**

```java
public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length < 2) {
        return intervals;
    }

    // 按照区间起始位置排序
    Arrays.sort(intervals, new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    });

    // 遍历区间
    List<int[]> res = new ArrayList<>();
    // 合并末端下标
    int index = 0;

    while (index < intervals.length) {
        int left = intervals[index][0];
        int right = intervals[index][1];

        // 判断重叠
        while (index < intervals.length - 1 &&
                intervals[index + 1][0] <= right) {
            index++;
            right = Math.max(right, intervals[index][1]);
        }

        res.add(new int[]{left, right});
        index++;
    }
    return res.toArray(new int[0][]);
}
```



### *Q6. Z 字形变换

将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。



**个人思路：**

找规律，按行访问，但是没成功，这里复制官方解法和代码，太难了，官方更推荐按列访问。

![](https://s1.ax1x.com/2020/08/08/aIIgCq.png)

代码：

```java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}
```



**思路：**

整体的思路是遍历字符串，遍历过程中将每行都看成新的字符串构成字符串数组，最后再将该数组拼接起来即可

如果 numRows=1 则说明当前字符串即为结果，直接返回

否则整个字符串需要经历，向下向右，向下向右，这样的反复循环过程，设定 down 变量表示是否向下，loc 变量表示当前字符串数组的下标

如果 down 为 true，则 loc += 1，字符串数组下标向后移动，将当前字符加入当前字符串中

如果 down 为 false，则表示向右，则 loc -= 1，字符串数组下标向前移动，将当前字符加入当前字符串中

时间复杂度：O(n)，n 为字符串s的长度

**代码：**

```java
public String convert(String s, int numRows) {
    if (s == null || numRows <= 1 || s.length() <= numRows) {
        return s;
    }

    String[] rows = new String[numRows];

    // 当前 rows 所在行下标
    int index = 0;


    // 所在行是否需要向下（index++）
    boolean down = false;

    // 初始化
    for (int i = 0; i < numRows; i++) {
        rows[i] = "";
    }

    for (int i = 0; i < s.length(); i++) {
        rows[index] += s.substring(i,i+1);

        // 到达两端需要改变方向
        if(index == 0 || index == numRows - 1){
            down = !down;
        }

        // 换行
        index += down ? 1 : -1;
    }

    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
        ans.append(rows[i]);
    }
    
    return String.valueOf(ans);
    
}
```



### *Q14. 最长公共前缀

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 `""`。

**个人思路：**

将第一个字符串做为标的，分别与后面的字符串比较，如果标的字符串为空，则代表不存在公共前缀，返回""

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
        return "";
    }

    if (strs.length == 1) {
        return strs[0];
    }

    // 第一个字符串做为比对标本
    String str = strs[0];

    for (int i = 1; i < strs.length; i++) {
        String temp = "";
        for (int j = 1; j <= Math.min(str.length(), strs[i].length()); j++) {
            if (str.substring(0, j).equals(strs[i].substring(0, j))) {
                temp = str.substring(0, j);
            }
        }
        if (temp.equals("")) {
            return "";
        } else {
            str = temp;
        }

    }

    return str;
}
```



**思路：**

1 横向扫描，如上

2 纵向扫描，即从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。

<img src="https://assets.leetcode-cn.com/solution-static/14/14_fig2.png" style="zoom: 25%;" />

3 分治

<img src="https://assets.leetcode-cn.com/solution-static/14/14_fig3.png" style="zoom:33%;" />



4 二分

显然，最长公共前缀的长度不会超过字符串数组中的最短字符串的长度。用 minLength 表示字符串数组中的最短字符串的长度，则可以在[0,minLength] 的范围内通过二分查找得到最长公共前缀的长度。每次取查找范围的中间值 mid，判断每个字符串的长度为 mid 的前缀是否相同，如果相同则最长公共前缀的长度一定大于或等于 mid，如果不相同则最长公共前缀的长度一定小于 mid，通过上述方式将查找范围缩小一半，直到得到最长公共前缀的长度。

<img src="https://assets.leetcode-cn.com/solution-static/14/14_fig4.png" style="zoom:25%;" />

**代码：**

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
```



### *763. 划分字母区间

字符串 `S` 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。



**个人思路：**

知道要用map存储字符出现的最后位置，接下来就不知道了，大菜鸡就是在下



**思路：**

看见开头一个字母，就要看这个字母最后出现在哪，然后康康这个开头和结尾之间的那些其他字母是不是都落在这个区间里，如果不是，则扩充区间，如果是，那就最好了

另一点是确定用什么方法，很多题你不看参考答案真的想不出来要走哪条路，倒不是说这条路有多难，而是不能确定到底应该往哪个方向去想。

对于这道题，一个关键点是利用字典key的唯一性来记载一个字母最大的index是几。
然后，遍历字符串并跟它比较，用max函数来康康是边界大还是当前遍历的这个字母的最大index大，如果当前遍历字母的最大index大，那就说明超过了边界，那就要更新边界，大概就是这个思路。



官方称这种思想是贪心，我咋没看出来呢，还是太菜了

**代码：**

```java
public List<Integer> partitionLabels(String S) {
    ArrayList<Integer> list = new ArrayList<>();

    if (S == null || S.length() == 0) {
        list.add(0);
        return list;
    }
    if (S.length() == 1) {
        list.add(1);
        return list;
    }

    // 存放字符最后出现位置
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < S.length(); i++) {
        map.put(S.charAt(i), i);
    }

    int start = 0, end = 0;

    // 遍历字符串并判断是否达到边界
    for (int i = 0; i < S.length(); i++) {
        end = Math.max(end, map.get(S.charAt(i)));

        // 遍历位置与边界重合
        if (i == end) {
            list.add(end - start + 1);
            start = end + 1;
        }
    }

    return list;
}
```



### *Q7. 整数反转

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

**注意:**

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31, 2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。



**个人思路：**

将整数转为字符串反转后转化为整数，如抛出异常则返回0

```java
public int reverse(int x) {
    if (x == 0) {
        return 0;
    }

    // 翻转之后的整数字符串
    String s;
    if (x > 0) {
        s = new StringBuffer(String.valueOf(x)).reverse().toString();
    } else {
        s = "-" + new StringBuffer(String.valueOf(x)).reverse().toString()
                .substring(0, String.valueOf(x).length()-1);
    }

    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return 0;
    }
}
```

这样写估计面试官不会很满意，Offer么了



**思路：**

字符串转换、使用库函数会大大降低效率，因此不是很合适，应该要考虑使用数学方法实现

- 通过循环将数字`x`的每一位拆开，在计算新值时每一步都判断是否溢出。
- 溢出条件有两个，一个是大于整数最大值`MAX_VALUE`，另一个是小于整数最小值`MIN_VALUE`，设当前计算结果为`ans`，下一位为`pop`。
- 从`ans * 10 + pop > MAX_VALUE`这个溢出条件来看
  - 当出现 `ans > MAX_VALUE / 10` 且 `还有pop需要添加` 时，则一定溢出
  - 当出现 `ans == MAX_VALUE / 10` 且 `pop > 7` 时，则一定溢出，`7`是`2^31 - 1`的个位数
- 从`ans * 10 + pop < MIN_VALUE`这个溢出条件来看
  - 当出现 `ans < MIN_VALUE / 10` 且 `还有pop需要添加` 时，则一定溢出
  - 当出现 `ans == MIN_VALUE / 10` 且 `pop < -8` 时，则一定溢出，`8`是`-2^31`的个位数

**代码：**



### Q8. 字符串转换整数

请你来实现一个 `atoi` 函数，使其能将字符串转换成整数。

**个人思路：**

与上题类似，先得到整数字符串再进行转换

```java
public int myAtoi(String str) {
    if (str == null || str.length() == 0) {
        return 0;
    }
    String num = "";

    boolean isStart = false;

    for (int i = 0; i < str.length(); i++) {
        boolean isNumber = '0' <= str.charAt(i) && str.charAt(i) <= '9';
        if (!isStart) {
            if (str.charAt(i) == ' ') {
                continue;
            }
            if (isNumber || str.charAt(i) == '-') {
                num += str.charAt(i);
                isStart = true;
            } else if (str.charAt(i) == '+') {
                isStart = true;
            } else {
                break;
            }
        } else {
            if (isNumber) {
                num += str.charAt(i);
            } else {
                break;
            }
        }
    }

    // 字符串转换整数
    if (num.equals("-") || num.equals("")) {
        return 0;
    }

    try {
        return Integer.parseInt(num);
    } catch (NumberFormatException e) {
        if (num.charAt(0) == '-') {
            return Integer.MIN_VALUE;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
```



### Q9. 回文数

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。



**个人思路：**

与 Q7 翻转整数类似，只不过不需要判断溢出

```java
public boolean isPalindrome(int x) {
    if (x < 0) {
        return false;
    }

    if (x < 9) {
        return true;
    }

    return x == reverse(x);

}


/**
 * 反转数 不会溢出
 * @param x
 * @return
 */
public int reverse(int x) {
    int ans = 0;
    while (x != 0) {
        int pop = x % 10;
        ans = ans * 10 + pop;
        x /= 10;
    }
    return ans;
}
```



### *Q43. 字符串相乘

给定两个以字符串形式表示的非负整数 `num1` 和 `num2`，返回 `num1` 和 `num2` 的乘积，它们的乘积也表示为字符串形式。



**个人思路：**

尝试模拟手工运算，失败



**思路：**

整个计算过程大概是这样，**有两个指针 `i，j` 在 `num1` 和 `num2` 上游走，计算乘积，同时将乘积叠加到 `res` 的正确位置**，

还有一个关键问题，如何将乘积叠加到 `res` 的正确位置，或者说，如何通过 `i，j` 计算 `res` 的对应索引呢？

其实，细心观察之后就发现，**`num1[i]` 和 `num2[j]` 的乘积对应的就是 `res[i+j]` 和 `res[i+j+1]` 这两个位置**。

<img src="https://pic.leetcode-cn.com/7793f394be437a8a0ed96e76bacadf567e08547517b993e2952fbca9a2493772.jpg" alt="img" style="zoom:25%;" />

明白了这一点，就可以用代码模仿出这个计算过程了。



**代码：**

```java
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0
                || num1.equals("0") || num2.equals("0")
        ) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();

        // m * n 最多有 m + n 位
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int num = ((num1.charAt(i) - '0') * (num2.charAt(j) - '0'));
                // 乘积再 res 对应的索引位置
                int p1 = i + j;
                int p2 = i + j + 1;
                // 叠加到 res 上
                int sum = num + res[p2];
                res[p2] = sum % 10;
                // 处理进位，如 19 * 19
                res[p1] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < res.length; i++) {
            // 首位出现0，跳过
            if (res[i] == 0 && i == 0) {
                continue;
            }
            result.append(res[i]);
        }

        return result.toString();
    }
```



### Q172. 阶乘后的零

给定一个整数 *n*，返回 *n*! 结果尾数中零的数量。

**个人思路：**

先把结果弄出来在判断尾数，很明显爆了

**思路：**

 首先题目的意思是末尾有几个0
    比如6! = 【1* 2* 3* 4* 5* 6】
    其中只有2*5末尾才有0，所以就可以抛去其他数据 专门看2 5 以及其倍数 毕竟 4 * 25末尾也是0
    比如10！ = 【2 * 4 * 5 * 6 * 8 * 10】
    其中 4能拆成2*2  10能拆成2*5 
    所以10！ = 【2 * （2 * 2） * 5 * （ 2 * 3 ） * （2 * 2 * 2） * （2 * 5）】
    一个2和一个5配对 就产生一个0 所以10！末尾2个0
     **转头一想 2肯定比5多 所以只数5的个数就行了**



**代码：**

```java
public int trailingZeroes(int n) {
    if (n < 5) {
        return 0;
    }

    // 实际上只要考虑统计 5 的个数
    // 如 10! = 【2*（2*2）*5*（2*3）*（2*2*2）*（2*5）】
    int count = 0;
    while (n >= 5) {
        count += n / 5;
        n = n / 5;
    }
    return count;
}
```



### Q258. 各位相加

给定一个非负整数 `num`，反复将各个位上的数字相加，直到结果为一位数。



**个人思路：**

使用递归完成

```java
public int addDigits(int num) {
    if (num < 0) {
        return 0;
    }

    if (num < 10) {
        return num;
    }

    int sum = 0;
    while (num != 0) {
        sum += num % 10;
        num /= 10;
    }
    return addDigits(sum);
}
```



**思路：**

除了上述常规方法，还可以找规律，使时间复杂度为O(1)

假设一个三位数整数n=100*a+10*b+c,变化后addn=a+b+c；
两者的差值n-addn=99a+9b，差值可以被9整除，说明每次缩小9的倍数
那么我们可以对res=num % 9，若不为0则返回res，为0则返回9

**代码：**

```java
int addDigits(int num) {
        if(num>9)
        {
            num=num%9;
            if(num==0)
                return 9;
        }
        return num;
    }
```



### Q54. 螺旋矩阵

给定一个包含 *m* x *n* 个元素的矩阵（*m* 行, *n* 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

**个人思路：**

模拟螺旋矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，当路径超出标记指针位置时，则顺时针旋转，进入下一个方向。

```java
        public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();

        // 定义上下左右指针（扫描边界）
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;

        // 扫描方向 right -> down -> left -> up -> ...
        int direction = 0;

        while (up <= down && left <= right) {
            direction = direction % 4;
            switch (direction) {
                // 向右
                case 0:
                    for (int i = left; i <= right; i++) {
                        ans.add(matrix[up][i]);
                    }
                    up++;
                    direction++;
                    break;
                // 向下
                case 1:
                    for (int i = up; i <= down; i++) {
                        ans.add(matrix[i][right]);
                    }
                    right--;
                    direction++;
                    break;
                // 向左
                case 2:
                    for (int i = right; i >= left; i--) {
                        ans.add(matrix[down][i]);
                    }
                    down--;
                    direction++;
                    break;
                // 向上
                case 3:
                    for (int i = down; i >= up; i--) {
                        ans.add(matrix[i][left]);
                    }
                    left++;
                    direction++;
                    break;
            }
        }
        return ans;
    }
```



**思路：**

除了模拟外，还可以标记访问过的元素，遇到访问过的元素顺时针改变方向即可



### Q73. 矩阵置零

给定一个 *m* x *n* 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用**[原地](http://baike.baidu.com/item/原地算法)**算法。

**个人思路：**

利用两个数组分别存储需要置零的行和列，遍历后对相关行列置零

```java
public void setZeroes(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length ==  0){
        return;
    }
    // 标记行
    int[] row = new int[matrix.length];
    // 标记列
    int[] line = new int[matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == 0){
                row[i] = 1;
                line[j] = 1;
            }
        }
    }

    // 行置 0
    for (int i = 0; i < row.length; i++) {
        if(row[i] == 1){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // 列置 0
    for (int i = 0; i < line.length; i++) {
        if(line[i] == 1){
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
```



**思路：**

空间复杂度 O(1) ，用两个布尔变量就可以解决。方法就是利用数组的首行和首列来记录 0 值。从数组下标的 A1 开始遍历，两个布尔值记录首行首列是否需要置0

```java
 public void setZeroes(int[][] matrix) {
        boolean rowFlag = false;
        //判断首行
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowFlag = true;
                break;
            }
        }

        boolean colFlag = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowFlag){
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colFlag){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
```



### Q78. 子集

给定一组**不含重复元素**的整数数组 *nums*，返回该数组所有可能的子集（幂集）。

**说明：**解集不能包含重复的子集。



**个人思路：**

使用递归 + 子集，从完整数组开始，每次减少一个元素组成新的数组去求其子集，效率太低了，没脸放上来



**思路：**

反过来，开始假设输出子集为空，每一步都向子集添加新的整数，并生成新的子集。

**代码：**

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(new ArrayList<Integer>());

    if (nums == null || nums.length == 0) {
        return ans;
    }

    for (Integer num : nums) {
        List<List<Integer>> newList = new ArrayList<>();
        for (List<Integer> cur : ans){
            newList.add(new ArrayList<Integer>(cur){{add(num);}});
        }

        ans.addAll(newList);
    }

    return ans;
}
```



### Q384. 打乱数组

打乱一个没有重复元素的数组。

**个人思路：**

使用 Random 函数

```java
public class Q384_ShuffleAnArray {
    final int[] original;
    int[] nums;

    // 判题需要修改 Class

    public Q384_ShuffleAnArray(int[] nums) {
        this.nums = nums;
        // 不加的话会复制 nums 的引用
        this.original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(nums.length);

            int temp = nums[index];
            nums[index] = nums[i];
            nums[i] = temp;

        }
        return nums;
    }
```

**思路：**

这道题难点在于随机打乱数组，返回的概率要相同，需要用到洗牌算法



### Q581. 最短无序子数组

 给定一个整数数组，你需要寻找一个**连续的子数组**，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是**最短**的，请输出它的长度。



**个人思路：**

将数组排序后，将两数组比较，并标记两个元素在原数组中的位置 i*i* 和 j*j* 。这两个元素标记着目前无序数组的边界

<img src="https://pic.leetcode-cn.com/78a1cebc4d1310e97c9bc201293d4a376d05cc7d3142b7794453332adf3ead14-image.png" style="zoom: 50%;" />

代码不贴了，太菜了

**思路：**

使用栈

![](https://s1.ax1x.com/2020/08/13/dSyx1J.png)

**代码：**

```java
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        Stack < Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
}
```



### Q945. 使数组唯一的最小增量

给定整数数组 A，每次 *move* 操作将会选择任意 `A[i]`，并将其递增 `1`。

返回使 `A` 中的每个值都是唯一的最少操作次数。

**个人思路：**

先排序后计数

```java
public int minIncrementForUnique(int[] A) {
    if (A == null || A.length <= 1) {
        return 0;
    }

    Arrays.sort(A);

    int ans = 0;
    int max = A[0];

    for (int i = 1; i < A.length; i++) {
        if (A[i] <= max) {
            ans += max - A[i] + 1;
            max++;
        } else {
            max = A[i];
        }
    }
    return ans;
}
```



**思路：**

上面方法中，排序需要 O(n log n)的时间，比较昂贵。我们尝试不进行排序的方法。

例如输入 `[3, 2, 1, 2, 1, 7]`，计数之后有两个 1 和两个 2。我们先看最小的数，两个 1 重复了，需要有一个增加到 2，这样 2 的数量变成了三个。在三个 2 中，又有两个需要增加到 3，然后又出现了两个 3…… 以此类推，可以计算出需要增加的次数。

我们可以用 map（如 C++ 的 `unordered_map`，Java 的 `HashMap`）来做计数。不过既然题目中说明了整数的范围在 0 到 40000 之间，我们不妨直接用一个大小为 40000 的数组做计数。

需要注意的是，虽然整数的范围是 0 到 40000，但是由于整数还会因为增加而变大，超出 40000 的范围。例如极端的情况：所有数都是 39999。所以需要对整数中最大的数单独处理。代码如下：public 

```java
int minIncrementForUnique(int[] A) {
    int[] count = new int[40000];
    int max = 0;
    for (int a : A) {
        count[a]++; // 计数
        max = Math.max(max, a); // 计算数组中的最大值
    }
    

    int res = 0;
    for (int j = 0; j < max; j++) {
        if (count[j] > 1) {
            // 有 count[j] - 1 个数需要增加
            res += count[j] - 1; 
            count[j+1] += count[j] - 1;
        }
    }
    
    // count[max] 单独计算，是因为可能超出 40000 的边界
    if (count[max] > 1) {
        int d = count[max] - 1; 
        // 有 d 个数需要增加
        // 分别增加为 max + 1, max + 2, ... max + d
        // 使用等差数列公式求和
        res += (1 + d) * d / 2;
    }
    
    return res;

}
```



### Q20. 有效的括号


给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。



**个人思路：**

这题使用辅助栈存储左括号，当遇到右括号的时候跟栈顶元素匹配，这道题要注意很多细节，包括`{{` 、`(()(`这样的情况

```java
public boolean isValid(String s) {
    if (s == null || s.length() == 0) {
        return true;
    }

    // 字符串长度为奇数，一定不匹配
        if (s.length() == 1 || s.length() % 2 == 1) {
            return false;
        }

    // 辅助栈，用于存储左括号进行匹配
    Stack<Character> stack = new Stack<>();


    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
            stack.push(s.charAt(i));
        } else {
            switch (s.charAt(i)) {
                case '}':
                    if (stack.size() > 0 && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.size() > 0 && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.size() > 0 && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
    }

    return stack.isEmpty();
}
```



### *Q32. 最长有效括号

给定一个只包含 `'('` 和 `')'` 的字符串，找出最长的包含有效括号的子串的长度。



**思路：**

- 在栈中预置 -1，希望它作为一个“参照物”，并改变计算方式：当前索引 - 出栈后新的栈顶索引
  ![image.png](https://pic.leetcode-cn.com/5d7c8630b67841475a97775c870fdb63cdfa317ce236a3335667700c5ac5f99f-image.png)
- 当遍历到索引 5 的右括号，此时栈顶的索引为 2，出栈，栈顶变为 -1，有效长度 = 5 - (-1) = 6。如果照之前那样，5 找不到 -1 减。
- 当遍历到索引 6 的右括号，它不是需要入栈的左括号，又匹配不到左括号，怎么处理？
- 它后面可能也出现一段有效长度，它要成为 -1 那样的“参照物”。它之前出现的有效长度已经计算过了，栈中的 -1 的使命已经完成了，要被替代了。
- 所以我们照常让 -1 出栈。不同的是，此时栈空了，让索引 6 入栈当“参照物”。

```java
public int longestValidParentheses(String s) {
    if (s == null || s.length() < 2) {
        return 0;
    }

    int ans = 0;
    // 存入栈的下标
    Stack<Integer> stack = new Stack<>();
    // 向栈中预置一个-1，将计算长度的方式转化成“）”的下标减去出栈后栈顶元素的下标
    stack.push(-1);

    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '('){
            stack.push(i);
        }else {
            stack.pop();
            // 如栈空，则注入信息 i 做为预置下标
            if (stack.isEmpty()){
                stack.push(i);
            }
            ans = Math.max(ans, i-stack.peek());
        }
    }

    return ans;
}
```



### Q155. 最小栈

设计一个支持 `push` ，`pop` ，`top` 操作，并能在常数时间内检索到最小元素的栈。

- `push(x)` —— 将元素 x 推入栈中。
- `pop()` —— 删除栈顶的元素。
- `top()` —— 获取栈顶元素。
- `getMin()` —— 检索栈中的最小元素。



**个人思路：**

主要解决问题是时间复杂度为 O(1) 的 getMin() ，本人使用辅助栈存储最小元素

```java
/** initialize your data structure here. */
public Q155_MinStack() {
    stack = new Stack<>();
    min = new Stack<>();
}

Stack<Integer> stack;
Stack<Integer> min;

public void push(int x) {
    stack.push(x);
    if (min.isEmpty() || min.peek() >= x){
        min.push(x);
    }
}

public void pop() {
    if (stack.pop().equals(min.peek())){
        min.pop();
    }
}

public int top() {
    return stack.peek();
}

public int getMin() {
    return min.peek();
}
```





### Q232. 用栈实现队列

使用栈实现队列的下列操作：

- push(x) -- 将一个元素放入队列的尾部。
- pop() -- 从队列首部移除元素。
- peek() -- 返回队列首部的元素。
- empty() -- 返回队列是否为空。



**个人思路：**

用两个栈实现，一个负责入队，一个负责出队



### *Q224. 基本计算器

实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式可以包含左括号 `(` ，右括号 `)`，加号 `+` ，减号 `-`，**非负**整数和空格 ` `。



**个人思路：**

本质上还是解决括号的问题，但是没解决



**思路：**

1 利用栈实现运算符反转

这题只要解决了括号的问题就很简单了。再加上本题有个限定条件是只有加和减两种操作，所以在处理括号时只需要考虑括号前的符号：

- 如果`(`前是`-`，那么反转括号内的所有运算符（+变成-，-变成+）
- 如果`(`前是`+`，则不需要反转括号内的运算符
- 遇到`)`时，恢复成上一层级的反转规则

用一个栈来保存当前的运算符反转规则，true代表反转，false代表不反转，栈为空时也不反转

以`1-(2-(3+4)-5)+6`为例说明：

- 第一个`(`前的运算符是`-`，将反转规则`true`入栈，栈内容`[true]`，已处理的算式为`1-2`
- 下一个运算符是`-`，而当前栈顶元素为`true`，故将其反转，此时已处理的算式为`1-2+`
- 第二个`(`前的运算符是`+`（刚刚反转的），故将反转规则`false`入栈，栈内容`[false,true]`，已处理的算式为`1-2+3`
- 下一个运算符是`+`，当前栈顶元素为`false`，不需要反转，已处理的算式为`1-2+3+4`
- 遇到`)`，将栈顶元素弹出，栈内容`[true]`，下一个运算符为`-`，根据当前栈顶元素，需要进行反转，已处理的算式为`1-2+3+4+5`
- 再次遇到`)`，将栈顶元素弹出，此时栈为空
- 下一个运算符是`+`，此时栈为空，不需要反转，最终处理完成的算式为`1-2+3+4+5+6`
- 现在算式中已经没有括号，直接进行运算即可得出最终结果`17`

运算符的处理和运算可以在一趟迭代中完成，故算法时间复杂度为O(N)。需要一个栈来保存括号前的运算符，实际使用的辅助空间取决于算式内括号的层数，极限的辅助空间为O(N)



```java
class Solution {
    public int calculate(String s) {
        LinkedList<Boolean> stack = new LinkedList<>();
        int result = 0, opr = 0; //result: 当前的结果值; opr: 当前的被加/被减数
        Character op = null; //当前要执行的运算符
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-') {
                if (op == null) {
                    //遇到第一个运算符时，将result置为opr（即第一个运算符左边的数字）
                    result = opr;
                } else {
                    //result = result +/- opr;
                    result = cal(op, result, opr);
                }
                //根据栈顶元素决定是否反转运算符
                op = swap(stack.peek() == null ? false : stack.peek(), c);
                opr = 0;
            } else if (c == '(') {
                stack.push(op != null && op == '-');
            } else if (c == ')') {
                stack.pop();
            } else if (c != ' ') {
                opr = opr * 10 + c - '0';
            }
        }
        if (op == null) {
            //算式中没有运算符时，opr就是最终结果
            return opr;
        } else {
            //否则将result与opr（即算式中最右边的数字）执行一次运算
            return cal(op, result, opr);
        }
    }

    char swap(boolean swap, char c) {
        if (swap) {
            return c == '+' ? '-' : '+';
        } else {
            return c;
        }
    }
    
    private int cal(char op, int opr1, int opr2) {
        switch (op) {
            case '+':
            return opr1 + opr2;
            case '-':
            return opr1 - opr2;
            default:
            return 0;
        }
    }

}
```

2 逆波兰式

这道题还能用逆波兰式做

待补充



### 四联

https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-6/

#### 402. 移掉 K 位数字

给定一个以字符串表示的非负整数 *num*，移除这个数中的 *k* 位数字，使得剩下的数字最小。



**思路：**

这道题让我们从一个字符串数字中删除 k 个数字，使得剩下的数最小。也就说，我们要保持原来的数字的相对位置不变。

以题目中的 `num = 1432219， k = 3` 为例，我们需要返回一个长度为 4 的字符串，问题在于： 我们怎么才能求出这四个位置依次是什么呢？

<img src="https://pic.leetcode-cn.com/953f4e530680455e3b4a55c5e631ccb6d61ea79d119371fffde5eab1c4312abd.jpg" alt="img" style="zoom:25%;" />

暴力法的话，我们需要枚举`C_n^(n - k)` 种序列（其中 n 为数字长度），并逐个比较最大。这个时间复杂度是指数级别的，必须进行优化。

一个思路是：

- 从左到右遍历
- 对于每一个遍历到的元素，我们决定是**丢弃**还是**保留**

问题的关键是：我们怎么知道，一个元素是应该保留还是丢弃呢？

这里有一个前置知识：**对于两个数 123a456 和 123b456，如果 a > b， 那么数字 123a456 大于 数字 123b456，否则数字 123a456 小于等于数字 123b456**。也就说，两个**相同位数**的数字大小关系取决于第一个不同的数的大小。

因此我们的思路就是：

- 从左到右遍历
- 对于遍历到的元素，我们选择保留。
- 但是我们可以选择性丢弃前面相邻的元素。
- 丢弃与否的依据如上面的前置知识中阐述中的方法。

以题目中的 `num = 1432219， k = 3` 为例的图解过程如下：

<img src="https://pic.leetcode-cn.com/6e8de5adf28d6a1e9b946e41ec96d6bcc137b43b96f42077bb8dc441028f2724.jpg" alt="img" style="zoom:25%;" />

由于没有左侧相邻元素，因此**没办法丢弃**。

<img src="https://pic.leetcode-cn.com/ca4de9ef8a7dcfaa1c80d9bdd89b92de67fe4489c977f948284c52a456f53a41.jpg" alt="img" style="zoom:25%;" />

由于 4 比左侧相邻的 1 大。如果选择丢弃左侧的 1，那么会使得剩下的数字更大（开头的数从 1 变成了 4）。因此我们仍然选择**不丢弃**。

<img src="https://pic.leetcode-cn.com/9d909a7091b50198f4568447431eca1487c0bb33c16d3fe1601373697b1b1a0c.jpg" alt="img" style="zoom:25%;" />

由于 3 比左侧相邻的 4 小。 如果选择丢弃左侧的 4，那么会使得剩下的数字更小（开头的数从 4 变成了 3）。因此我们选择**丢弃**。

。。。

后面的思路类似，我就不继续分析啦。

然而需要注意的是，如果给定的数字是一个单调递增的数字，那么我们的算法会永远**选择不丢弃**。这个题目中要求的，我们要永远确保**丢弃** k 个矛盾。

一个简单的思路就是：

- 每次丢弃一次，k 减去 1。当 k 减到 0 ，我们可以提前终止遍历。
- 而当遍历完成，如果 k 仍然大于 0。不妨假设最终还剩下 x 个需要丢弃，那么我们需要选择删除末尾 x 个元素。

上面的思路可行，但是稍显复杂。



我们需要把思路逆转过来。刚才我的关注点一直是**丢弃**，题目要求我们丢弃 k 个。反过来说，不就是让我们保留 n - k 个元素么？其中 n 为数字长度。 那么我们只需要按照上面的方法遍历完成之后，再截取前**n - k**个元素即可。



```java
public String removeKdigits(String num, int k) {
    if (num == null || k >= num.length()) {
        return "0";
    }

    LinkedList<Character> list = new LinkedList<>();

    for (Character c : num.toCharArray()) {

        // 第一个数字，直接存入
        if (list.isEmpty()) {
            list.addLast(c);
        } else {
            while (list.size() > 0 && c < list.peekLast() && k > 0) {
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
    }
    // 遍历完一轮后仍然没有移除足够位数，需要删除最后k位
    for (int i = 0; i < k; i++) {
        list.removeLast();
    }

    // 移除前面的 0
    while (!list.isEmpty()) {
        if (list.peekFirst() == '0') {
            list.removeFirst();
        } else {
            break;
        }
    }

    StringBuilder newStr = new StringBuilder();
    for (Character c : list) {
        newStr.append(c);
    }

    return list.isEmpty() ? "0" : newStr.toString();
}
```



#### *Q316. 去除重复字母

给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

1. 若栈中已经有当前元素，则直接去除当前元素

2. 若当前的栈顶元素比当前的元素字典序大，且当前元素的位置后面还有栈顶元素,
   将栈顶元素出栈, 将当前元素入栈, 这样来找到最优的排列



总的来说：遇到一个新字符 如果比栈顶小 并且在新字符后面还有和栈顶一样的 就把栈顶的字符抛弃了

```java
public String removeDuplicateLetters(String s) {
    if (s == null || s.length() < 2) {
        return s;
    }

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
        Character c = s.charAt(i);

        // 若栈中已经有当前元素，则直接去除当前元素
        if (stack.contains(c)) {
            continue;
        }

        // 若当前的栈顶元素比当前的元素字典序大，且当前元素的位置后面还有栈顶元素,
        //将栈顶元素出栈, 将当前元素入栈, 这样来找到最优的排列
        while (!stack.empty() && stack.peek() > c &&
                s.indexOf(stack.peek(), i) != -1) {
            stack.pop();
        }
        stack.push(c);
    }

    char[] res = new char[stack.size()];
    for (int i = 0; i < stack.size(); i++) {
        res[i] = stack.get(i);
    }
    return new String(res);
}
```



### *Q215. 数组重的第 K 个最大元素

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**个人思路：**

虽然知道要用快排，但是忍不住写了个sort

**思路：**

1 基于快速排序的选择方法

以下是注意事项，因为很重要，所以放在前面说：

> **快速排序虽然快，但是如果实现得不好，在遇到特殊测试用例的时候，时间复杂度会变得很高。如果你使用 `partition` 的方法完成这道题，时间排名不太理想，可以考虑一下是什么问题，这个问题很常见。**

以下的描述基于 “快速排序” 算法知识的学习，如果忘记的朋友们可以翻一翻自己的《数据结构与算法》教材，复习一下，partition 过程、分治思想和 “快速排序” 算法的优化。

分析：我们在学习 “快速排序” 的时候，接触的第 1 个操作就是 partition（切分），简单介绍如下：

partition（切分）操作，使得：

- 对于某个索引 `j`，`nums[j]` 已经排定，即 `nums[j]` 经过 partition（切分）操作以后会放置在它 “最终应该放置的地方”；
- `nums[left]` 到 `nums[j - 1]` 中的所有元素都不大于 `nums[j]`；
- `nums[j + 1]` 到 `nums[right]` 中的所有元素都不小于 `nums[j]`。

![image.png](https://pic.leetcode-cn.com/65ec311c3e9792bb17e9c08cabd4a07f251c9cd65a011b6c5ffb54b46d8e5012-image.png)

partition（切分）操作总能排定一个元素，还能够知道这个元素它最终所在的位置，这样每经过一次 partition（切分）操作就能缩小搜索的范围，这样的思想叫做 “减而治之”（是 “分而治之” 思想的特例）。

切分过程可以不借助额外的数组空间，仅通过交换数组元素实现。下面是参考代码：

```java
public class Solution {

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        // 转换一下，第 k 大元素的索引是 len - k
        int target = len - k;

        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    /**
     * 在数组 nums 的子区间 [left, right] 执行 partition 操作，返回 nums[left] 排序以后应该在的位置
     * 在遍历过程中保持循环不变量的语义
     * 1、[left + 1, j] < nums[left]
     * 2、(j, i] >= nums[left]
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int j = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                // 小于 pivot 的元素都被交换到前面
                j++;
                swap(nums, j, i);
            }
        }
        // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
        swap(nums, j, left);
        // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
        return j;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
```



2 优先队列

优先队列的思路是很朴素的。因为第 `K` 大元素，其实就是整个数组排序以后后半部分最小的那个元素。因此，我们可以维护一个有 `K` 个元素的最小堆：

1、如果当前堆不满，直接添加；

2、堆满的时候，如果新读到的数小于等于堆顶，肯定不是我们要找的元素，只有新都到的数大于堆顶的时候，才将堆顶拿出，然后放入新读到的数，进而让堆自己去调整内部结构。

说明：这里最合适的操作其实是 `replace`，即直接把新读进来的元素放在堆顶，然后执行下沉（`siftDown`）操作。Java 当中的 `PriorityQueue` 没有提供这个操作，只好先 `poll()` 再 `offer()`。

优先队列的写法就很多了，这里例举一下我能想到的（以下的写法大同小异，没有本质差别）。

假设数组有 `len` 个元素。

思路1：把 `len` 个元素都放入一个最小堆中，然后再 `pop()` 出 `len - k` 个元素，此时最小堆只剩下 `k` 个元素，堆顶元素就是数组中的第 `k` 个最大元素。

思路2：把 `len` 个元素都放入一个最大堆中，然后再 `pop()` 出 `k - 1` 个元素，因为前 `k - 1` 大的元素都被弹出了，此时最大堆的堆顶元素就是数组中的第 `k` 个最大元素。



优先队列可以用一个 for 循环解决哈。就是在 for 循环里面判断小顶堆里面的 size() 是否大于 k 个数，是的话就 poll() 出去；整个 for 循环结束之后剩下来的就是 k 个数的小顶堆。堆顶即第 k 大的数。

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }
}
```



### Q347. 前 K 个高频元素

给定一个非空的整数数组，返回其中出现频率前 **k** 高的元素。



题目最终需要返回的是前 k 个频率最大的元素，可以想到借助堆这种数据结构，对于 k 频率之后的元素不用再去处理，进一步优化时间复杂度。

<img src="https://pic.leetcode-cn.com/2b27b1db106a53abe239c5be8e49a800522ab2f6637940cb556bcfe1232ff333-file_1561712388055" alt="img" style="zoom:33%;" />

具体操作为：

- 借助 **哈希表** 来建立数字和其出现次数的映射，遍历一遍数组统计元素的频率
- 维护一个元素数目为 k的最小堆
- 每次都将新的元素与堆顶元素（堆中频率最小的元素）进行比较
- 如果新的元素的频率比堆顶端的元素大，则弹出堆顶端的元素，将新的元素添加进堆中
- 最终，堆中的 k个元素即为前 k个高频元素

<img src="https://pic.leetcode-cn.com/b548a3796066fa7072baa2b1e06e0d54641a7913d87c88c61d73b6b9ad0e90db-file_1561712388100" alt="堆中的元素就是前 k 个频率最大的元素" style="zoom: 50%;" />

代码：

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    // 遍历数组获取每个元素的出现次数
    for (Integer num: nums) {
        if (!map.containsKey(num)){
            map.put(num,1);
        }else {
            map.put(num,map.get(num)+1);
        }
    }

    // 遍历map，用最小堆保存频率最大的 k 个元素
    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return map.get(a) - map.get(b);
        }
    });

    for (Integer num:map.keySet()){
        if (pq.size() < k){
            pq.add(num);
        }else if (map.get(num) > map.get(pq.peek())){
            pq.remove();
            pq.add(num);
        }
    }

    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
        ans[i] = pq.remove();
    }

    return ans;
}
```



### Q21. 合并两个有序列表

将两个升序链表合并为一个新的 **升序** 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**个人思路：**

使用递归，题解代码比本人代码简洁，故贴上官解代码

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
```

除了这个方法外还有迭代，此处略



### Q101. 对称二叉树

给定一个二叉树，检查它是否是镜像对称的。



**个人思路：**

使用递归

```java
public boolean isSymmetric(TreeNode root) {
    if (root == null){
        return true;
    }

    return dfs(root.left,root.right);
}

private boolean dfs(TreeNode left, TreeNode right) {
    if (left == null && right == null){
        return true;
    }

    if (left == null || right == null){
        return false;
    }

    if (left.val != right.val){
        return false;
    }

    return dfs(left.left,right.right) && dfs(left.right, right.left);
}
```



递归的难点在于：找到可以递归的点 为什么很多人觉得递归一看就会，一写就废。 或者说是自己写无法写出来，关键就是你对递归理解的深不深。

对于此题： 递归的点怎么找？从拿到题的第一时间开始，思路如下：

1.怎么判断一棵树是不是对称二叉树？ 答案：如果所给根节点，为空，那么是对称。如果不为空的话，当他的左子树与右子树对称时，他对称

2.那么怎么知道左子树与右子树对不对称呢？在这我直接叫为左树和右树 答案：如果左树的左孩子与右树的右孩子对称，左树的右孩子与右树的左孩子对称，那么这个左树和右树就对称。

仔细读这句话，是不是有点绕？怎么感觉有一个功能A我想实现，但我去实现A的时候又要用到A实现后的功能呢？

当你思考到这里的时候，递归点已经出现了： 递归点：我在尝试判断左树与右树对称的条件时，发现其跟两树的孩子的对称情况有关系。

想到这里，你不必有太多疑问，上手去按思路写代码，函数A（左树，右树）功能是返回是否对称

def 函数A（左树，右树）： 左树节点值等于右树节点值 且 函数A（左树的左子树，右树的右子树），函数A（左树的右子树，右树的左子树）均为真 才返回真

实现完毕。。。

写着写着。。。你就发现你写出来了。。。。。。



### Q104. 二叉树的最大深度

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:** 叶子节点是指没有子节点的节点。



**个人思路：**

递归

```java
public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    return depth(root, 0);
}

private int depth(TreeNode root, int level) {
    if (root == null) {
        return level;
    }
    return  Math.max(depth(root.left, level+1), depth(root.right, level+1));
}
```



### Q226. 翻转二叉树

翻转一棵二叉树。

**个人思路：**

递归

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null||(root.left == null && root.right == null)){
        return root;
    }else {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    invertTree(root.left);
    invertTree(root.right);

    return root;
}
```



### *Q236. 二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。



**思路：**

要想找到两个节点的最近公共祖先节点，我们可以从两个节点往上找，每个节点都往上走，一直走到根节点，那么根节点到这两个节点的连线肯定有相交的地方，如果是从上往下走，那么最后一次相交的节点就是他们的最近公共祖先节点。我们就以找6和7的最近公共节点来画个图看一下

<img src="https://pic.leetcode-cn.com/0f72cfd7bb18b5310a619cbeb313f6c968fdf95d904bafed86018bd429725099-image.png" alt="image.png" style="zoom:50%;" />

我们看到6和7公共祖先有5和3，但最近的是5。我们只要往上找，找到他们第一个相同的公共祖先节点即可，但怎么找到每个节点的父节点呢，我们只需要把每个节点都遍历一遍，然后顺便记录他们的父节点存储在Map中。我们先找到其中的一条路径，比如6→5→3，然后在另一个节点往上找，由于7不在那条路径上，我们找7的父节点是2，2也不在那条路径上，我们接着往上找，2的父节点是5，5在那条路径上，所以5就是他们的最近公共子节点。

其实这里我们可以优化一下，我们没必要遍历所有的结点，我们一层一层的遍历（也就是BFS），只需要这两个节点都遍历到就可以了，比如上面2和8的公共结点，我们只需要遍历到第3层，把2和8都遍历到就行了，没必要再遍历第4层了。



**递归编写思路：**

**最近公共祖先的引申定义：**
对于两个节点`p`、`q`和一棵树`root`
1.如果树中存在`p`、`q`则按题目定义；
2.如果树中只存在`p`，则`p`、`q`公共祖先是`p`；如果只存在`q`，`p`、`q`公共祖先是`q`；
3.如果树中`p`、`q`都不存在，则公共祖先是`null`（这条实际上可以被包括在2中）；
以下所提到的“**公共祖先**”均代表“**引申定义下的最近公共祖先**”

**递归函数形参返回值：**
传入待考察树根节点`root`和两个条件`p`、`q`
返回`p`、`q`在`root`中的公共祖先

**递归函数终止条件：**
找到公共祖先或者`root`为空
（实际上`root==null`也是一种找到公共祖先的情况`定义3`，可与去掉这个表述）

**递归函数功能：**
对于传入的树，找到`p`、`q`的公共祖先。（对每个根结点都是如此）

功能具体实现：

1. 如果`root`等于`p`，返回`p`，因为：
   - 如果树中有`q`，根据`定义1`，则`p`肯定是公共祖先，返回`p`
   - 如果树中无`q`，根据`定义2`，则`p`也是公共祖先，仍然返回`p`
2. 如果`root`等于`q`，同上返回`q`
3. 如果`root==null`，说明`p`、`q`都不在树中（空树），根据`定义3`，返回`null`
4. 如果以上不满足，说明`p`、`q`**要么在子树中**，**要么不在树中**，对`root`左右子节点调用函数
   - 如果右子树为空，说明公共祖先在左子树中，会通过返回值呈递上来
   - 如果左子树为空，说明公共祖先在右子树中，会通过返回值呈递上来
   - 如果都为空，实际上也包含在上面两种情况中，返回`null`
   - 如果都不为空，说明`p`、`q`分别在左右子树中，那么根节点`root`就是`p`、`q`公共祖先



```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q){
        return root;
    }

    TreeNode left = lowestCommonAncestor(root.left,p,q);
    TreeNode right = lowestCommonAncestor(root.right,p,q);

    if (left == null){
        return right;
    }

    if (right == null){
        return left;
    }

    return root;
}
```



### *Q23. 合并K个升序链表

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。



**个人思路：**

逐个合并，效率太低

**思路：**

分而治之

```java
   public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists,0,lists.length-1);
    }

    // 分治
    private ListNode merge(ListNode[] lists,int left, int right) {
        if (left == right){
            return lists[left];
        }

        int mid = left + (right - left)/2;

        ListNode a = merge(lists,left,mid);
        ListNode b = merge(lists,mid+1, right);

        return mergeTwoLists(a,b);
    }


    // 合并两个有序列表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }

        if (l2 == null){
            return l1;
        }

        if (l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
```



### Q33. 搜索旋转排序数组

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 `-1` 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 *O*(log *n*) 级别。

**个人思路：**

由于时间复杂度的限制，需要使用二分排序

由于题目说数字了无重复，举个例子：
1 2 3 4 5 6 7 可以大致分为两类，
第一类 2 3 4 5 6 7 1 这种，也就是 `nums[start] <= nums[mid]`。此例子中就是 `2 <= 5`。
这种情况下，前半部分有序。因此如果 `nums[start] <=target<nums[mid]`，则在前半部分找，否则去后半部分找。
第二类 6 7 1 2 3 4 5 这种，也就是 `nums[start] > nums[mid]`。此例子中就是 `6 > 2`。
这种情况下，后半部分有序。因此如果 `nums[mid] <target<=nums[end]`，则在后半部分找，否则去前半部分找。

```java
public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return -1;
    }

    return binarySort(nums, 0, nums.length - 1, target);

}

private int binarySort(int[] nums, int start, int end, int target) {
    if (start == end) {
        if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }

    int mid = start + (end - start) / 2;

    // 左半部分有序
    if (nums[start] <= nums[mid]) {
        if (nums[start] <= target && target <= nums[mid]) {
            return binarySort(nums, start, mid, target);
        } else {
            return binarySort(nums, mid + 1, end, target);
        }
    } else {
        // 右半部分有序
        if (nums[mid + 1] <= target && target <= nums[end]) {
            return binarySort(nums, mid + 1, end, target);
        } else {
            return binarySort(nums, start, mid, target);
        }
    }
}
```



### Q34. 在排序数组中查找元素的第一个和最后一个位置

给定一个按照升序排列的整数数组 `nums`，和一个目标值 `target`。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 *O*(log *n*) 级别。

如果数组中不存在目标值，返回 `[-1, -1]`。



**个人思路：**

使用二分查找分别查找元素的第一个和最后一个位置

```java
public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return new int[]{-1, -1};
    }

    int begin = leftSort(nums, 0, nums.length - 1, target, -1);
    int end = rightSort(nums, 0, nums.length - 1, target, -1);

    return new int[]{begin, end};
}

private int leftSort(int[] nums, int begin, int end, int target, int index) {

    if (begin >= end) {
        if (nums[begin] == target) {
            return begin;
        } else {
            return index;
        }
    }

    int mid = begin + (end - begin) / 2;
    if (nums[mid] == target) {
        return leftSort(nums, begin, mid - 1, target, mid);
    }

    if (nums[mid] < target) {
        return leftSort(nums, mid + 1, end, target, index);
    }

    if (nums[mid] > target) {
        return leftSort(nums, begin, mid - 1, target, index);
    }
    return -1;
}

private int rightSort(int[] nums, int begin, int end, int target, int index) {
    if (begin >= end) {
        if (nums[begin] == target) {
            return begin;
        } else {
            return index;
        }
    }

    int mid = begin + (end - begin) / 2;
    if (nums[mid] == target) {
        return rightSort(nums, mid+1, end, target, mid);
    }

    if (nums[mid] < target) {
        return rightSort(nums, mid + 1, end, target, index);
    }

    if (nums[mid] > target) {
        return rightSort(nums, begin, mid - 1, target, index);
    }
    return -1;
}
```



### Q5. 最长回文子串

给定一个字符串 `s`，找到 `s` 中最长的回文子串。你可以假设 `s` 的最大长度为 1000。

**思路：**

动态规划听起来很高大上。其实说白了就是空间换时间，将计算结果暂存起来，避免重复计算。作用和工程中用 `redis` 做缓存有异曲同工之妙。
我们用一个 `boolean dp[l][r]` 表示字符串从 `i` 到 `j` 这段是否为回文。试想如果 `dp[l][r]=true`，我们要判断 `dp[l-1][r+1]` 是否为回文。只需要判断字符串在(`l-1`)和（`r+1`)两个位置是否为相同的字符，是不是减少了很多重复计算。
进入正题，动态规划关键是找到初始状态和状态转移方程。
初始状态，`l=r` 时，此时 `dp[l][r]=true`。
状态转移方程，`dp[l][r]=true` 并且(`l-1`)和（`r+1`)两个位置为相同的字符，此时 `dp[l-1][r+1]=true`。



```java
 public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }


        // 初始化 dp 二维数组
        boolean dp[][] = new boolean[s.length()][s.length()];

        int start = 0;
        int end = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }


        for (int r = 1; r < s.length(); r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLength) {
                        maxLength = r - l + 1;
                        start = l;
                        end = r;

                    }
                }

            }

        }

        return s.substring(start, end + 1);
    }
```



### Q53. 最大子序和

- 这道题用动态规划的思路并不难解决，比较难的是后文提出的用分治法求解，但由于其不是最优解法，所以先不列出来
- 动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
- 如果 `sum > 0`，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
- 如果 `sum <= 0`，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
- 每次比较 `sum` 和 `ans`的大小，将最大值置为`ans`，遍历结束返回结果
- 时间复杂度：O(n)



```java
public int maxSubArray(int[] nums) {
    if (nums == null){
        throw new IllegalArgumentException("Nums Is Null!");
    }

    if (nums.length == 0){
        return 0;
    }

    if(nums.length == 1){
        return nums[0];
    }

    int len = nums.length;
    int max = nums[0];
    // 初始化 dp
    int[] dp = new int[len];
    dp[0] = nums[0];

    for (int i = 1; i < len; i++) {
        dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
        max = Math.max(max, dp[i]);
    }

    return max;
}
```



### Q62. 不同路径

一个机器人位于一个 *m x n* 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

例如，上图是一个7 x 3 的网格。有多少可能的路径？



**个人思路：**

动态规划



我们令 `dp[i][j]` 是到达 `i, j` 最多路径

动态方程：`dp[i][j] = dp[i-1][j] + dp[i][j-1]`

注意，对于第一行 `dp[0][j]`，或者第一列 `dp[i][0]`，由于都是在边界，所以只能为 `1`

时间复杂度：O(m * n)

空间复杂度：O(m * n)

```java
public int uniquePaths(int m, int n) {
    if (m <= 0 || n <= 0) {
        return 0;
    }

    if (m == 1 || n == 1) {
        return 1;
    }

    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;
    }

    for (int i = 0; i < n; i++) {
        dp[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }

    return dp[m - 1][n - 1];
}
```

**思路：**

因为机器到底右下角，向下几步，向右几步都是固定的，

比如，m=3, n=2，我们只要向下 1 步，向右 2 步就一定能到达终点。

所以有 C【
m+n−2
m−1

】



### Q64. 最小路径和

给定一个包含非负整数的 *m* x *n* 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。



**个人思路：**

动态规划，思路与上题类似

```java
public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
    }

    int m = grid.length;
    int n = grid[0].length;

    int[][] dp = new int[m][n];

    dp[0][0] = grid[0][0];

    for (int i = 1; i < m; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }

    for (int i = 1; i < n; i++) {
        dp[0][i] = dp[0][i - 1] + grid[0][i];
    }


    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    return dp[m - 1][n - 1];
}
```



### Q70. 爬楼梯



**个人思路：**

爬到第 x 级台阶的方案数是爬到第 x - 1 级台阶的方案数和爬到第 x - 2 级台阶的方案数的和

```java
public int climbStairs(int n) {
    if (n <= 0){
        return 0;
    }

    if (n == 1){
        return 1;
    }

    if (n == 2){
        return 2;
    }

    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i-1]+ dp[i-2];
    }

    return dp[n];
}
```



### Q118. 杨辉三角

给定一个非负整数 *numRows，*生成杨辉三角的前 *numRows* 行。

<img src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif" alt="img" style="zoom:50%;" />

在杨辉三角中，每个数是它左上方和右上方的数的和。



**个人思路**

n = 1，row = 1

n = 2，row = 1, 1

n = 3，row = 1，2，1

...

n = i，row = 1，row(i-1)[i-1] + row(i-1)[i]，…，1

（n > 2时，第 i 行的首尾元素时1外，其余情况，第 i 个元素是第 i - 1行第 i-1、i 个元素之和）

```java
public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < numRows ; i++) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j <= i ; j++) {
            if (j == 0 || j == i){
                list.add(1);
            }else {
                list.add(ans.get(i-1).get(j) + ans.get(i-1).get(j-1));
            }
        }
        ans.add(list);
    }
    return ans;
}
```



### *Q300. 最长上升子序列

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**思路：**

子序列的问题->动态规划。

- 使用数组 `cell` 保存每步子问题的最优解。
- `cell[i]` 代表含第 `i` 个元素的最长上升子序列的长度。
- 求解 `cell[i]` 时，向前遍历找出比 `i` 元素小的元素 `j`，令 `cell[i]` 为 `max（cell[i],cell[j]+1)`。



```java
public int lengthOfLIS(int[] nums) {
    if (nums == null) {
        return 0;
    }
    int len = nums.length;
    if (len <= 1) {
        return len;
    }

    int[] dp = new int[len];
    dp[0] = 1;
    int max = 1;

    for (int i = 1; i < len; i++) {
        int maxDp = 0;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                maxDp = Math.max(maxDp, dp[j]);
            }
        }
        dp[i] = maxDp + 1;
        max = Math.max(max,dp[i]);
    }
    return max;
}
```



### *Q1143. 最长公共子序列

给定两个字符串 `text1` 和 `text2`，返回这两个字符串的最长公共子序列的长度。

一个字符串的 *子序列* 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

 **思路：**

https://leetcode-cn.com/problems/longest-common-subsequence/solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/

视频讲解

https://leetcode-cn.com/problems/longest-common-subsequence/solution/shi-pin-jiang-jie-shi-yong-dong-tai-gui-hua-qiu-ji/

**第一步，一定要明确 `dp` 数组的含义**。对于两个字符串的动态规划问题，套路是通用的。

比如说对于字符串 `s1` 和 `s2`，一般来说都要构造一个这样的 DP table：

<img src="https://pic.leetcode-cn.com/8c371d5bbba8c503329992ac36464f04d11e6617a550bfad2cfcb4e0e7965bf9.png" alt="img" style="zoom:33%;" />

为了方便理解此表，我们暂时认为索引是从 1 开始的，待会的代码中只要稍作调整即可。其中，`dp[i][j]` 的含义是：对于 `s1[1..i]` 和 `s2[1..j]`，它们的 LCS 长度是 `dp[i][j]`。

比如上图的例子，d[2][4] 的含义就是：对于 `"ac"` 和 `"babc"`，它们的 LCS 长度是 2。我们最终想得到的答案应该是 `dp[3][6]`。

**第二步，定义 base case。**

我们专门让索引为 0 的行和列表示空串，`dp[0][..]` 和 `dp[..][0]` 都应该初始化为 0，这就是 base case。

比如说，按照刚才 dp 数组的定义，`dp[0][3]=0` 的含义是：对于字符串 `""` 和 `"bab"`，其 LCS 的长度为 0。因为有一个字符串是空串，它们的最长公共子序列的长度显然应该是 0。

**第三步，找状态转移方程。**

这是动态规划最难的一步，不过好在这种字符串问题的套路都差不多，权且借这道题来聊聊处理这类问题的思路。

状态转移说简单些就是做选择，比如说这个问题，是求 `s1` 和 `s2` 的最长公共子序列，不妨称这个子序列为 `lcs`。那么对于 `s1` 和 `s2` 中的每个字符，有什么选择？很简单，两种选择，要么在 `lcs` 中，要么不在。

<img src="https://pic.leetcode-cn.com/b54067c447c5b4d68821b41f0ab5e9f531c79cf25de4b2583ccc41e97e1e611d.png" alt="img" style="zoom:33%;" />

这个「在」和「不在」就是选择，关键是，应该如何选择呢？这个需要动点脑筋：如果某个字符应该在 `lcs` 中，那么这个字符肯定同时存在于 `s1` 和 `s2` 中，因为 `lcs` 是最长**公共**子序列嘛。所以本题的思路是这样

```java
public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null) {
        return 0;
    }

    int len1 = text1.length();
    int len2 = text2.length();
    char[] t1 = text1.toCharArray();
    char[] t2 = text2.toCharArray();

    if (len1 == 0 || len2 == 0) {
        return 0;
    }

    int[][] dp = new int[len1 + 1][len2 + 1];

    for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
            if (t1[i - 1] == t2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[len1][len2];
}
```



## Q107. 二叉树的层次遍历 Ⅱ

给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其自底向上的层次遍历为：

```
[
  [15,7],
  [9,20],
  [3]
]
```



**个人思路：**

先进行 BFS 搜索，将每一层的遍历结果存入栈中，在逐个出栈达到效果。

**思路：**

使用链表，每扫描完一层后，将结果插入链表头部，无需使用栈

```java
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> ans = new LinkedList<>();
    if (root == null) {
        return ans;
    }

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.pop();
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null){
                queue.add(node.right);
            }
        }

        // 将得到的一层结点加入结果集
        ans.add(0,list);
    }

    return ans;
}
```



### Q538. 把二叉搜索树转换为累加树

※ 2020/9/21 每日一题

给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

**例如：**

```
输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13
```

**思路：**

二叉搜索树定义（https://www.jianshu.com/p/ff4b93b088eb）：

二叉搜索树是一种节点值之间具有一定数量级次序的二叉树，对于树中每个节点：

- 若其左子树存在，则其左子树中每个节点的值都不大于该节点值；

- 若其右子树存在，则其右子树中每个节点的值都不小于该节点值。

  ![img](https://upload-images.jianshu.io/upload_images/9738807-6b37320f910e1fb7.png?imageMogr2/auto-orient/strip|imageView2/2/w/311/format/webp)



此时采用后序遍历，可以得到降序排列的元素，我们只需要在遍历的时候用一个变量存储累加值即可实现累加树。

```java
public class Q538_convertBST {
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if(root != null){
            // 后序遍历可以达到遍历二叉搜索树从大到小排序，从而实现累加
            convertBST(root.right);
            root.val += sum;
            sum = root.val;
            convertBST(root.left);
        }

        return root;
    }
}
```
### Q617. 合并二叉树

※2020/9/23 每日一题

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
```


注意: 合并必须从两个树的根节点开始。



**思路：**

深度优先搜索，可以使用深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，并将对应的节点进行合并。

两个二叉树的对应节点可能存在以下三种情况，对于每种情况使用不同的合并方式。

- 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
- 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
- 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和，此时需要显性合并两个节点。

对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。



**代码：**

```java
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode node = null;

        if(t1 != null || t2 != null ){
            
            //t1 t2都有结点
            if(t1 != null && t2 != null){
                node = new TreeNode(t1.val + t2.val);
                node.left = mergeTrees(t1.left, t2.left);
                node.right = mergeTrees(t1.right, t2.right);
            }else if(t1 != null){
                // 仅 t1 有结点
                // node = new TreeNode(t1.val);
                // node.left = mergeTrees(t1.left, null);
                // node.right = mergeTrees(t1.right, null);
                return t1;
            }else {
                 // 仅 t2 有结点
                // node = new TreeNode(t2.val);
                // node.left = mergeTrees(null, t2.left);
                // node.right = mergeTrees(null, t2.right);
                return t2;
            }
        }

        return node;
    }
```

### Q501. 二叉树中的众数

※2020/9/24 每日一题

给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

- 结点左子树中所含结点的值小于等于当前结点的值
- 结点右子树中所含结点的值大于等于当前结点的值
- 左子树和右子树都是二叉搜索树

例如：
给定 BST `[1,null,2,2]`,

```
   1
    \
     2
    /
   2
```

`返回[2]`

**提示**：如果众数超过1个，不需考虑输出顺序

**进阶：**你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）





**思路：**

这道题我的思路是先利用中序遍历得到升序排列的数组，再统计众数，但是过程过于复杂，贴个代码先

```java
public int[] findMode(TreeNode root) {
        ArrayList<Integer> list = order(root);
        ArrayList<Integer> result = new ArrayList<>();
        int curCount = 0;
        int maxCount = 0;
        Integer cur = null;

        int size = list.size();

        for(int i = 0;i<size;i++){
            Integer e = list.get(i);
            if(i == 0){
                cur = e;
                curCount++;
                continue;
            }

            if(cur.equals(e)){
                curCount++;
            }else {
                if(curCount > maxCount){
                    result.clear();
                    result.add(cur);
                    maxCount = curCount;
                    cur = e;
                    curCount = 1;
                }else if(curCount == maxCount){
                    result.add(cur);
                    cur = e;
                    curCount = 1;
                }else{
                    cur = e;
                    curCount = 1;
                }
            }

            if(i == size - 1){
                if(curCount > maxCount){
                    result.clear();
                    result.add(cur);
                }else if(curCount == maxCount){
                    result.add(cur);
                }
            }
        }

        int[] ans = new int[result.size()];

        for(int i = 0;i<result.size();i++){
            ans[i] = result.get(i);
        }

        return ans;
    }

    public ArrayList<Integer> order(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        list.addAll(order(root.left));
        list.add(root.val);
        list.addAll(order(root.right));

        return list;
    }
```



后面看题解，发现可以将众数识别代码提取出来，然后直接在中序遍历的时候比较即可得出，修改后代码如下：

```java
public class Q501_FindMode {

    int cur;
    int curCount;
    int maxCount;
    List<Integer> answer = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        order(root);
        int[] result = new int[answer.size()];

        for(int i = 0;i<answer.size();i++){
            result[i] = answer.get(i);
        }

        return result;
    }

    public void order(TreeNode root){
        if(root == null){
            return ;
        }
        order(root.left);
        update(root.val);
        order(root.right);
    }

    // 众数比较
    public void update(int x){
        if (x == cur){
            curCount++;
        }else{
            curCount = 1;
            cur = x;
        }

        if(curCount == maxCount){
            answer.add(cur);
        }

        if (curCount > maxCount){
            maxCount = curCount;
            answer.clear();
            answer.add(cur);
        }
    }
```

### *Q106.从中序与后序遍历序列构造二叉树

根据一棵树的中序遍历与后序遍历构造二叉树。

※2020/9/25 每日一题

**个人思路：**

递归，每次中序遍历和后序遍历都可以得到当前树的根节点，然后再分别弄成左右子树递归解决问题

效率太低了

```java
public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder == null || postorder == null){
        return null;
    }

    if(postorder.length == 0 || inorder.length != postorder.length){
        return null;
    }


    int len = postorder.length;

    // 根节点下标
    int headIndex = -1;

    for(int i = 0;i < len;i++){
        if(postorder[len-1] == inorder[i]){
            headIndex = i;
            break;
        }
    }


    // 根节点
    TreeNode head = new TreeNode(postorder[len-1]);

    // 左子树元素长度
    int left = headIndex;
    // 右子树元素长度
    int right = len-left-1;

    int[] leftInorder = new int[left];
    int[] leftPostorder = new int[left];

    int[] rightInorder = new int[right];
    int[] rightPostorder = new int[right];
    if(left != 0){
        System.arraycopy(inorder,0,leftInorder,0,left);
        System.arraycopy(postorder,0,leftPostorder,0,left);
    }

    if(right != 0){
        System.arraycopy(inorder,headIndex+1,rightInorder,0,right);
        System.arraycopy(postorder,left,rightPostorder,0,right);
    }

    head.left = buildTree(leftInorder,leftPostorder);
    head.right = buildTree(rightInorder,rightPostorder);
    return head;
}
```


### Q12. 整数转罗马数字

罗马数字包含以下七种字符： `I`， `V`， `X`， `L`，`C`，`D` 和 `M`。

```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

例如， 罗马数字 2 写做 `II` ，即为两个并列的 1。12 写做 `XII` ，即为 `X` + `II` 。 27 写做 `XXVII`, 即为 `XX` + `V` + `II` 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 `IIII`，而是 `IV`。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 `IX`。这个特殊的规则只适用于以下六种情况：

- `I` 可以放在 `V` (5) 和 `X` (10) 的左边，来表示 4 和 9。
- `X` 可以放在 `L` (50) 和 `C` (100) 的左边，来表示 40 和 90。 
- `C` 可以放在 `D` (500) 和 `M` (1000) 的左边，来表示 400 和 900。

给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。



**个人思路：**

将给定的整数转换为罗马数字需要找到上述 13 个符号的序列，这些符号的对应值加起来就是整数。根据符号值，此序列必须按从大到小的顺序排列。符号值如下。

<img src="https://img-blog.csdnimg.cn/20200414105909472.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTEzOTUwNQ==,size_16,color_FFFFFF,t_70" alt="在这里插入图片描述" style="zoom:50%;" />

如概述中所述，表示应该使用尽可能大的符号，从左侧开始工作。因此，使用贪心算法是有意义的。贪心算法是一种在当前时间做出最佳可能决策的算法；在这种情况下，它会取出最大可能的符号。

为了表示一个给定的整数，我们寻找适合它的最大符号。我们减去它，然后寻找适合余数的最大符号，依此类推，直到余数为0。我们取出的每个符号都附加到输出的罗马数字字符串上。

```java
public String intToRoman(int num) {
        if(num < 1 || num > 3999){
            return "error";
        }

        String result = "";

        while(num != 0){
            while(num / 1000 != 0){
                result += "M";
                num -= 1000;
                continue;
            }

            while(num / 900 != 0){
                result += "CM";
                num -= 900;
                continue;
            }

            while(num / 500 != 0){
                result += "D";
                num -= 500;
                continue;
            }

            while(num / 400 != 0){
                result += "CD";
                num -= 400;
                continue;
            }

            while(num / 100 != 0){
                result += "C";
                num -= 100;
                continue;
            }

            while(num / 90 != 0){
                result += "XC";
                num -= 90;
                continue;
            }

            while(num / 50 != 0){
                result += "L";
                num -= 50;
                continue;
            }

            while(num / 40 != 0){
                result += "XL";
                num -= 40;
                continue;
            }

            while(num / 10 != 0){
                result += "X";
                num -= 10;
                continue;
            }

            while(num / 9 != 0){
                result += "IX";
                num -= 9;
                continue;
            }

            while(num / 5 != 0){
                result += "V";
                num -= 5;
                continue;
            }

            while(num / 4 != 0){
                result += "IV";
                num -= 4;
                continue;
            }

            while(num / 1 != 0){
                result += "I";
                num -= 1;
                continue;
            }
        }
        return result;
    }
```

当然官方写法比我简洁多了（自己有个问题，遇到需要经常变化的字符串应该使用StringBuilder/StringBuffer而非String以提高效率）

```java
int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

public String intToRoman(int num) {
    StringBuilder sb = new StringBuilder();
    // Loop through each symbol, stopping if num becomes 0.
    for (int i = 0; i < values.length && num >= 0; i++) {
        // Repeat while the current symbol still fits into num.
        while (values[i] <= num) {
            num -= values[i];
            sb.append(symbols[i]);
        }
    }
    return sb.toString();
}
```

### Q117. 填充每个节点的下一个右侧节点指针 II

※2020/9/28 每日一题

给定一个二叉树

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 `NULL`。

初始状态下，所有 next 指针都被设置为 `NULL`。

 

**进阶：**

- 你只能使用常量级额外空间。
- 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。



**思路：**

广度优先搜索，层次遍历二叉树即可

```java
public Node connect(Node root) {
    LinkedList<Node> list = new LinkedList<>();
    if(root == null){
        return root;
    }

    list.add(root);
    int size = list.size();
    while(size > 0){
        for(int i = 0;i < size; i++){
            Node node = list.removeFirst();

            if(node.left != null){
                list.add(node.left);
            }

            if(node.right != null){
                list.add(node.right);
            }
            // 该层最后一个元素
            if(i == size - 1){
                node.next = null;
            }else{
                node.next = list.peek();
            }
        }

        size = list.size();
    }
    return root;
}

/**
 * 结点 Node 定义
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
```



### Q701. 二叉搜索树中的插入操作

※2020/9/30 每日一题
提前祝大家节日快乐

给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

 

例如, 

```
给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5
```

你可以返回这个二叉搜索树:

```
         4
       /   \
      2     7
     / \   /
    1   3 5
```

或者这个树也是有效的:

```
         5
       /   \
      2     7
     / \   
    1   3
         \
          4
```

 

**提示：**

- 给定的树上的节点数介于 `0` 和 `10^4` 之间
- 每个节点都有一个唯一整数值，取值范围从 `0` 到 `10^8`
- `-10^8 <= val <= 10^8`
- 新值和原始二叉搜索树中的任意节点值都不同

**思路：**

深度优先搜索，当前val的适当位置 并 插入

```java
public TreeNode insertIntoBST(TreeNode root, int val) {
    if(root == null){
        return new TreeNode(val);
    }

    TreeNode node = root;
    while(true){
        if(val > node.val){
            if(node.right == null){
                node.right = new TreeNode(val);
                break;
            }else{
                node = node.right;
                continue;
            }
        }

        if(val < node.val){
            if(node.left == null){
                node.left = new TreeNode(val);
                break;
            }else{
                node = node.left;
                continue;
            }
        }

        if(val == node.val){
            if(node.left == null){
                node.left = new TreeNode(val);
                break;
            }

            if(node.right == null){
                node.right = new TreeNode(val);
                break;
            }else{
                node = node.right;
                continue;
            }
        }
    }

    return root;
}
```


