public class DojeDoublyLinkedList<E>{
    private Node<E> head; // 노드의 첫 부분을 가리키는 레퍼런스
    private Node<E> tail; // 노드의 끝 부분을 가리키는 레퍼런스

    private int size; // 리스트 요소 갯수

    private static class Node<E> {
        private E data; // Node에 담을 데이터
        private Node<E> next; // 다음 Node 객체를 가르키는 래퍼런스
        private Node<E> prev; // 이전 Node 객체를 가르키는 래퍼런스

        // 생성자 (이전 노드 포인트 | 데이터 | 다음 노드 포인트)
        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    private  Node<E> search(int index)
    {
        Node<E> current;
        if(index>=size/2)
        {
            //index가 뒤에서 출발하는게 더 가까울때
            current = tail;
            for(int i=size-1;i>index;i--)
            {
                current = current.prev;
            }
        }
        else
        {
            //index가 앞에서 출발하는게 더 가까울때
            current = head;
            for(int i=0; i<index; i++)
            {
                current = current.next;
            }
        }
        return current;
    }
    public void addFirst(E obj)// 첫번째 위치에 요소 추가
    {
        //head가 참조하고 있는 노드를 가져온다.
        Node temp = head;
        // 새로운 노드를 생성하는데 이때 next값에 기존에 head였던 노드의 참조값을 넣어 준다.
        Node newNode = new Node(null, obj, temp);
        //head를 최산화 해준다.
        head = newNode;

        if(size==0)
        {
            //이번 입력이 처음인 경우 tail또한 현재 추가한 노드를 참조한다.
            tail = newNode;
        }
        else
        {
            //기존에 있던 값이 있다면 그값의 prev에 새로운 노드를 참조한다.
            temp.prev = newNode;
        }
        //크기를 증가시켜준다.
        size++;
    }

    public void addLast(E obj)//마지막 위치에 요소 추가
    {
        //head가 참조하고 있는 노드를 가져온다.
        Node temp = tail;
        // 새로운 노드를 생성하는데 이때 prev값에 기존에 tail이였던 노드의 참조값을 넣어 준다.
        Node newNode = new Node(temp, obj, null);
        //tail을 최산화 해준다.
        tail = newNode;
        if(size==0)
        {
            //이번 입력이 처음인 경우 tail또한 현재 추가한 노드를 참조한다.
            head = newNode;
        }
        else
        {
            //기존에 있던 값이 있다면 그값의 prev에 새로운 노드를 참조한다.
            temp.next = newNode;
        }
        //크기를 증가시켜준다.
        size++;
    }

    public boolean add(E obj)//마지막 위치에 요소 추가
    {
        //addLast와 작동이 동일하기에 메소드를 호출한다.
        addLast(obj);
        return true;
    }

    public void add(int index, E element) //지정된 위치에 요소 추가
    {
        if(index<0 || index>=size)
        {
            //인덱스가 0보다 작거나 size보다 크거나 같은경우에는 예외처리해준다.
            throw new IndexOutOfBoundsException();
        }
        //index가 가장 처음인 경우에는 addFirest를 호출한다.
        if(index==0)
        {
            addFirst(element);
            return;
        }
        //index가 가장 처음인 경우에는 addLast를 호출한다.
        if(index==size-1)
        {
            addLast(element);
            return;
        }
        Node temp_prev = search(index-1);
        Node temp_next = temp_prev.next;
        Node newNode = new Node(temp_prev, element, temp_next);
        temp_prev.next = newNode;
        temp_next.prev = newNode;
        size++;
    }

}
