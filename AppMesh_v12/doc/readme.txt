https://github.com/payomdousti/Trie

[keychar][children size]
[children 1 char][children 1 pos]...

addr:
[block len]
[terminal][prefixVal]
[children len]
[childrenKeys ch1...][childrenValues addr1...]

节点结构：
[字符值][前缀值]
[[子字符值][子位置值]...]

字节结构：
file start 000000: [head size]
file start 000004: [[keychar_1][addr_1]...] (align array)
body start addr_1: [isTerminal][children size] 
                   [[keychar_11]...] (align array)
                   [[addr_11]...] (align array)
				   [prefixVal_1]
body start addr_2: ...
