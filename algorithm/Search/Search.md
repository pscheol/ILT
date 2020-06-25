#탐색(Search)

#### 순차탐색(Sequential search = Linear Search)

-	데이터의 집합을 처음부터 마지막까지 순차적으로 찾고하는 데이터를 비교하여 탐색하는 방법
-	처음부터 끝가지 모든 요소를 검사

```cs
void int sequential_search(int data) {
    int i=0;
    for(i=0; i<100; i++) {
        if (data == data[i]) {
            return data[i];
        }
    }
    return 0;
}
```

##### 자기구성 순차탐색

-	자주 찾는 항목, 자주사용하는 목록들을 다른 항목보다 우선하여 접근할 수 있게 가까이 배치하는 방법
	-	전진이동법 (Move To Front) : 항목이 한번 탐색되고 나면 그 항목을 데이터 집합의 가장 앞에 위치시키는

```cs
  Node* MoveTOFront(Node** head, int target) {
    Node* current = (*head);
    Node* previous = NULL;
    Node* match = NULL;

    while(current != NULL) {
      if (current.data == target) {
        match = current;
        if (previous != NULL) {
            previous->next = current->next;
            current->next = (*head);
            (*head) = current;
        }
        break;
      }
      else {
          previous = current;
          current = current->next;
      }
    }
    return match;
  }
```

-	전위법 (Transpose) : 탐색된 항목을 바로 이전 항목과 교환(같은 데이터가 자주 탐색될 수록 앞으로 이동)

```cs
  Node* Transpose(Node** head, int target) {
    Node* current = (*head);
    Node* pprevious = NULL;
    Node* previous = NULL;
    Node* match = NULL;

    while(current != NULL) {
      if (current.data == target) {
        match = current;
        if (previous != NULL) {
          if (pprevious != NULL) {
            pprevious->next = current;
          }
          else {
            (*head) = current;
          }
          previous->next = current->next;
          current->next = previous;
        }
        break;
      }
      else {
        if (previous != null) {
          pprevious = previous;  
        }
        previous = current;
        current = current->next;
      }
    }
    return match;
  }
```

-	빈도계수법 (Frequency Count) : 데이터 집합 내의 각 요소들이 탐색된 횟수를 별도의 공간에 저장해두고, 탐색된 횟수가 높은 순으로 데이터 집합을 재구성하는 알고리즘

#### 이진탐색(Binary Search)

-	탐색범위를 1/2씩 줄여나가는 알고리즘
	1.	데이터의 집합의 중앙에 있는 요소를 선택
	2.	중앙 요소 값과 찾고자 하는 목표 값을 비교
	3.	목표값이 중앙 요소 값보다 작으면 중앙을 기준으로 왼편에 대해 새로 검색하고, 중앙 요소 값보다 크면 오른편에 대해 새로검색
	4.	찾고자하는 값을 찾을 때 까지 1~3번 반복

```java
public int binarySearch(int n[], int target) {
		int left, right, mid;
		int length = n.length;

		left = 0;
		right = length-1;

		while(left <= right) {
			mid = (left + right) / 2;
			if (n[mid] == target) {
				return n[mid];
			}
			if (n[mid] > target) {
				right = mid - 1;
			}
			else if(n[mid] < target) {
				left = mid + 1;
			}
		}
		return 0;
	}
```

#### 이진 탐색 트리 (Binary Search Tree)

1.	탐색 : 왼쪽 자식노드는 부모노드보다 작고, 오른쪽 자식노드는 부모노드보다 크다

	```cs
	    BSTNode* BST_Search(BSTNode* tree, Element target) {
	      if (tree == NULL) {
	        return NULL;
	      }
	      if (tree->data == target) {
	        return tree;
	      } else if (tree->data > target) {
	        return BST_Search(tree->left, target);
	      } else {
	        return BST_Search(tree->right, target);
	      }
	    }
	```

2.	노드 삽입

	-	새 노드가 삽입될 곳은 이진 탐색을 통해 찾아내야 한다.
	-	이진 탐색을 통해 새 노드가 놓일 곳을 찾아낸 후 그곳에 노드를 추가하면된다.

	```cs
	  /**
	  * Recursion
	  **/
	  void BST_Insert(BSTNode** tree, BSTNode* newNode) {
	    if ((*tree)->data > newNode->data) {
	      if ((*tree)->right == NULL) {
	        (*tree)->right = newNode;
	      } else {
	        BST_Insert(&(*tree)->right, newNode);
	      }
	    }
	    else if ((*tree)->data < newNode->data) {
	      if ((*tree)->left == NULL) {
	        (*tree)->left = newNode;
	      } else {
	        BST_Insert(&(*tree)->left, newNode);
	      }
	    }
	  }
	  /**
	  * Loof
	  **/
	  void BST_Insert(BSTNode** tree, BSTNode* newNode) {
	    BSTNode* current = (*tree);
	    if (current == NULL) {
	      (*tree) = newNode;
	    }
	    while(current != NULL) {
	      if (current->data > newNode->data) {
	        if (current->left != NULL) {
	          current = current->left;  
	        }
	        else {
	          current->left = newNode;
	          break;
	        }
	      } else {
	        if (current->right != NULL) {
	          current = current->right;  
	        }
	        else {
	          current->right = newNode;
	          break;
	        }
	      }
	    }
	  }
	```

3.	노드삭제

	-	Leaf 노드는 데이터를 찾고 NULL 초기화 시키면되지만 양쪽 자식노드를 가지고 있는경우는?

		1.	양쪽 자식노드를 모두 갖고 있는 경우

			-	삭제된 노드의 오른쪽 하위트리에서 가장 작은 값을 가진노드를 삭제된 위치에 옮겨 놓는다.

		2.	왼쪽/오른쪽 중 어느 한쪽 자식 노드만 갖고 있는 경우

			-	삭제된 노드의 부모노드가 자식 노드를 연결시키면 된다.

```cs

BSTNode* BST_Remove(BSTNode* tree, BSTNode* parent, Element target) {
  BSTNode* removed = NULL;
  if (tree == NULL) {
    return NULL;
  }

  if (tree->data > target) {
    removed = BST_Remove(tree->left, tree, target);
  }
  else if (tree->data < target) {
    removed = BST_Remove(tree->right, tree, target);
  }
  else {
    removed = tree;
    /*left node만 있는 경우*/
    if (tree->left == NULL && tree->right == NULL) {
        if (parent->left = NULL) {
          parent->left = NULL;
        }
        else {
          parent->right = NULL;
        }
    }
    else {
      /* 양쪽 다 있는 경우*/
      if (tree->left != NULL && tree->right != NULL) {
        BSTNode* minNode = BST_SearchMinNode(tree->right);
        Removed = BST_Remove(tree,NULL, minNode->data);
        tree->data = minNode->data;
      }
      else { //한쪽에만 있는경우
        BSTNode* temp = NULL;
        if (tree->left != NULL) {
          temp = tree->left;
        }
        else {
          temp = tree->right;
        }
        if (parent->left == tree) {
          parent->left= temp;
        }
        else {
          parent->right = temp;
        }
      }
    }
  }
  return removed;
}
BSTNode* BST_SearchMinNode(BSTNode* tree) {
  if (tree == NULL) {
    return NULL;
  }
  if (tree->left == NULL) {
    return tree;
  }
  else {
    return BST_SearchMinNode(tree->left);
  }
}
```

##### 이진 탐색 트리의 문제점

-	탐색 트리가 기형적으로 성장해서 검색의 효율을 극단적으로 떨어트림
-	그래서 계층을 정리해줄 필요가 있음...

#### Red Black Tree(레드 블랙 트리)

-	이진탐색의 균형을 잡아주는 트리
-	트리와 다른점은 노드를 빨간색 또는 검은색으로 표시한다.

Red Black Tree의 구조체

```cs
typedef int Element;

typedef struct _RBTNode {
    struct _RBTNode *left;
    struct _RBTNode *right;
    struct _RBTNode * parent;
    enum {RED, BLACK} Color;
    Element data;
}RBTNode;
```

##### 균형유지 방법

1.	모든 노드는 빨간색 아니면 검정색이다.
2.	루트노드는 검정색이다.
3.	Left노드는 검정색이다.
4.	빨간 노드의 자식들은 모두 검정색이다. 하지만 검정색 노드의 자식이 빨간색일 필요는 없다.
5.	루트노드에서 모든 잎 노드 사이에 있는 검정색 노드의 수는 모두 동일하다.

##### 기본연산

-	레드블랙 트리는 삽입이나 삭제를 할 경유 규칙이 무너진다.
-	무너진 규칙이 무너지면 레드블랙 트리가 아니다.
-	그래서 삽입과 삭제를 하고 후처리를 해주어야 함

###### 1. 회전(Route)

-	부모-자식 노드의 위치를 서로 바꾸는 연산
-	좌회전(Left-Route)과 우회전(Right-Route)로 나눈다.

	-	우회전(Right-Route) : 왼쪽 자식과 부모의 위치를 교환하는 것

		-	왼쪽 자식노드의 오른쪽 자식노드를 부모노드의 왼쪽 자식노드로 연결

	-	좌회줜(Left-Route) : 오른쪽 자식과 부모의 위치를 교환하는 것

		-	오른쪽 자식 노드의 왼쪽 자식노드를 부모 노드의 오른쪽 자식으로 연결

```cs
  void RBT_RouteRight(RBTNode** root, RBTNode* parent) {
    RBTNode* leftChild = parent->left;
    parent->left = leftChild->right;
    if (leftChild->right != Nil) {
      leftChild->right->parent = parent;
    }
    leftChild->parent = parent->parent;

    if (parent->parent == NULL) {
      (*root) = leftChild;
    }
    else {
      if (parent == parent->parent->left) {
        parent->parent->left = leftChild;
      }
      else {
        parent->parent->right = leftChild;
      }
    }
    leftChild->right = parent;
    parent->parent = leftChild;
  }
```
