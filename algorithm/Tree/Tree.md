Tree(트리)
==========

-	나무와 유사하게 비선형(데이터가 계층적 구조로 이루어짐) 구조로 이루어져 있는 자료구조
-	트리는 다른 자료구조보다 자료를 저장하거나 검색하는 방법이 간단하고 메모리를 효율적으로 사용할 수 있다.

#### 구성

-	트리는 크게 Root(뿌리), Branch(가지),leaf(잎) 세 가지 요소로 이루어짐
-	Root : 트리 구조에서 최상위에 존재하는 노드이다.
-	Branch : Root Node or Sub Tree 와 leaf 사이에 있는 노드를 말한다(자식). 
-	Leaf(Terminal Node) : Branch Node의 맨 끝에 달려있는 노드로, 밑으로 또 다른 노드가 연결되어 있지 않은 노드를 말한다(Terminal(단말)노드).

-	Node : 트리의 구성요소에 해당하는 요소를 말한다.

-	Edge : 노드와 노드를 연결하는 연결선이다.

-	Sub-Tree : 큰 트리(전체)에 속하는 작은 트리

-	Level(Depth) : 루트노드에서 해당 노드까지 경로의 길이로 트리에서 각 층별로 숫자를 매김

-	Height : 트리의 최고 레벨 (3)

-	Length : 출발 노드에서 목적 노드까지 거쳐야하는 노드의 개수

-	Degree(차수) : 해당 노드의 자식노드 개수를 말한다.

#### 트리의 표현

1.	중첩된 괄호(Nested Parenthesis) : 같은 레벨의 노드를 괄호로 묶어 표현
2.	중첩된 집합(Nested Set) : 트리를 집합관계로 표현
3.	들여쓰기(Indentation) : 들여쓰기로 표현된 트리

#### 노드 표현

부모와 자식, 형제노드를 서로 연결짓는 방법

1.	N-Link(N-링크 표현법) : 노드의 차수가 N개라면 노드가 N개의 링크를 가지고 있어서 이 링크들이 각각 자식 노드를 가리키도록 노드를 구성하는 방법(단점, 차수가 노드마다 달라지는 트리에서는 적용하기 어렵고 복잡한 트리를 만들게됨)
2.	Left Child-Right Sibling(왼쪽 자식, 오른쪽 형제 표현법) : N개의 차수를 가진 노드의 표현이 2개의 포인터(링크), 왼쪽-오른쪽 형제만 가진다.

#### 구현

-	노드의 선언

```cs
typedef struct _Node {
    int data;
    struct _Node *left;
    struct _Node *right;
}TreeNode;
```

-	노드의 생성

```cs
TreeNode* createNode(int data) {
  TreeNode newNode = (TreeNode*)malloc(sizeof(TreeNode));
  newNode->left = NULL;
  newNode->right = NULL;
  newNode->data = data;
  return newNode;
}
```

-	트리 연결

```cs
void addChildNode(TreeNode* parent, TreeNode* child) {
  if (parent->left == NULL) {
    parent->left = child;
  } else {
    TreeNode* tmpNode = parent->left;
    while(tmpNode->right != null) {
      tmpNode = tmpNode->right;
    }
    tmpNode->right = child;
  }
}
```

-	트리 출력

```cs
  void printTree(TreeNode* node, int depth) {
    int i = 0;
    for (int i =0; i<depth; i++) {
      printf(" ");
    }
    printf("%d\n", node->data);
    if (node->left != NULL) {
      printTree(node->left, depth+1);
    }
    if (node->right != NULL) {
      printTree(node->right, depth+1);
    }
  }
```
