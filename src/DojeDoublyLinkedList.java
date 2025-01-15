import java.util.Arrays;

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
    public E removeFirst()//가장 앞에 있는 Node를 제거 (제거한 요소는 반환)
    {
        //head에 값이 없을때 즉 빈 list일때 예외처리
        if(head==null)
        {
            throw new IndexOutOfBoundsException();
        }
        
        //head에 담겨있던 값을 백업
        E data = head.data;
        Node<E> temp = head.next;
        
        //head에 있던 값 삭제
        head.next = null;
        head.data = null;
        
        //head를 head.next값 넣어주기
        head = temp;


        //크기를 줄여준다.
        size--;
        //빈 list인 경우에 tail또한 null처리를 해주고, 빈 list가 아닌 경우에는 새로운 head의 prev를 null처리해준다.
        if(size==0)
        {
            tail = null;
        }
        else
        {
            head.prev = null;
        }

        return data;
    }
    public E remove()//가장 앞에 있는 Node를 제거
    {
        return removeFirst();
    }
    public E removeLast()//가장 뒤에 있는 Node를 제거
    {
        //빈 list일 경우 예외 처리
        if(head==null)
        {
            throw new IndexOutOfBoundsException();
        }
        //삭제할 node가 가지고 있던 요소를 백업
        E data = tail.data;
        Node<E> temp = tail.prev;

        //노드 데이터 삭제
        tail.prev = null;
        tail.data = null;

        //tail에 tail.prev값 넣어주기.
        tail = temp;

        //노드를 삭제했기에 크기를 줄여준다.
        size--;

        //빈 list인 경우에는 head도 null을 참조 시킨다.
        if(size==0)
        {
            head = null;
        }
        else
        {
            temp.next = null;
        }

        //삭제한 노드의 data를 반환해준다.
        return data;
    }
    public E remove(int index)//인덱스 위치의 요소를 제거
    {
        //index의 범위가 잘못된 경우 예외 발생
        if(index<0 || index>=size)
        {
            throw new IndexOutOfBoundsException();
        }
        
        //첫번째 index혹은 마지막 index를 입력받은 경우 기존에 구현해둔 코드를 이용
        if(index==0)
        {
            return removeFirst();
        }
        if(index==size-1)
        {
            return removeLast();
        }

        //삭제할 전 노드, 삭제할 노드, 삭제할 이후 노드를 저장.
        Node<E> temp_prev = search(index-1);
        Node<E> temp_del = temp_prev.next;
        Node<E> temp_next = temp_del.next;
        
        //data를 반환해주기 위해 미리 저장
        E data = temp_del.data;
        
        //node 삭제
        temp_del.next = null;
        temp_del.data = null;
        temp_del.prev = null;

        //앞뒤 노드 연결
        temp_prev.next = temp_next;
        temp_next.prev = temp_prev;

        //크기 감소
        size--;
        
        //미리 저장해둔 데이터 반환.
        return data;
    }
    public boolean remove(E value)//value값이 일치하는 위치의 Node를 제거(중복해서 값이 있는경우에는 가장 앞에 있는 Node를 제거)
    {
        //빈 list인 경우에 예외 발생
        if(size==0)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = head;
        for(int i=0;i<size;i++)
        {
            if(temp.data.equals(value))
            {
                remove(i);
                return true;
            }
            else
            {
                temp = temp.next;
            }
        }
        return false;
    }
    public E get(int index)
    {
        //index가 잘못 주어진 경우 예외 처리
        if(index<0 || index>=size)
        {
            throw new IndexOutOfBoundsException();
        }
        return search(index).data;
    }
    public void set(int index, E element)
    {
        if(index<0 || index>=size)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = search(index);

        temp.data = null;
        temp.data = element;
    }


}
