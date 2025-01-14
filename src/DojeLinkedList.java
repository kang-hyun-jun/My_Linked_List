import java.util.Objects;

public class DojeLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private static class Node<E>{
        private E data;
        private Node<E> next;
        Node(E data, Node<E> next)
        {
            this.data = data;
            this.next = next;
        }
        public String toString()
        {
            return String.valueOf(this.data);
        }
    }

    private Node<E> search(int index) {
        Node<E> temp = head;
        for(int i=0;i<index;i++)
        {
            temp= temp.next;
        }
        return temp;
    }
    public void addFirst(E obj)// 첫번째 위치에 요소 추가
    {
        //head요소를 가지고 온다
        Node<E> temp = head;
        //새로 추가할 노드를 생성한다 이때 value는 입력된값, 다음 노드는 head에 있던 값을 넣어준다.
        Node<E> newNode = new Node(obj, temp);
        //head가 새로운 노드를 가르키게 해준다.
        head = newNode;
        //size를 증가시킨다.
        size++;
        //temp 즉, 기존에 head가 null인 상태(현재 값이 처음인 경우) tail이 참조하는 값도 head와 같게 해준다.
        if(temp==null)
        {
            tail = newNode;
        }
    }

    public void addLast(E obj)//마지막 위치에 요소 추가
    {
        //tail이 참조하고 있는 Node를 temp에 담는다.
        Node<E> temp = tail;
        //새로 추가할 노드를 생성한다. 이때 value는 입력된 값을, 주소는 마지막 값이기에 tail을 넣어준다.
        Node<E> newNode = new Node(obj, null);
        //tail이 새로운 노드를 참고하게 한다.
        tail = newNode;
        //크기를 증가 시킨다.
        size++;
        if(temp==null)
        {
            //처음 값이 추가된 경우 head도 같은 값을 참조
            head = newNode;
        }
        else
        {
            //기존에 값이 있던 경우에는 새로 추가된 노드를 기존 tail의 노드의 next가 참조하게끔 한다.
            temp.next = newNode;
        }
    }

    public boolean add(E obj)//마지막 위치에 요소 추가
    {
        //작동 방식이 addLast와 동일하기에 메소드 호출로 대체한다.
        addLast(obj);
        return true;
    }

    public void add(int index, E element)//지정된 위치에 요소 추가
    {
        if(index<0 || index>=size)
        {
            //입력된 index가 linkedlist의 크기보다 크거나, 0보다 작은경우는 예외처리한다.
            throw new IndexOutOfBoundsException();
        }
        if(index==0)
        {
            //index가 0이라는 것은 처음에 추가하는 것이기에 기존에 구현한 코드를 이용한다.
            addFirst(element);
            return;
        }
        if(index==size-1)
        {
            //index가 마지막으로 주어진경우에는 마지막에 추가하는것은 기존에 구현한 코드를 이용한다.
            addLast(element);
            return;
        }

        //추가할 노드의 앞 뒤 노드를 이용해서 앞 노드의 next에는 새로운 노드주소값을, 새로운 노드의 next에는 뒤노드이 주소값을 참조시킨다.
        Node<E> temp = search(index-1);
        Node<E> temp_next = temp.next;
        Node<E> newNode = new Node(element, temp_next);
        temp.next = newNode;
        size++;
    }

    public E removeFirst()//가장 앞에 있는 Node를 제거 (제거한 요소는 반환)
    {
        //삭제할 요소가 하나도 없는경우에는 예외 발생
        if(size==0)
        {
            throw new IndexOutOfBoundsException();
        }
        //head의 data를 반환 해줘야 하기에 미리 저장해둔다.
        E data = head.data;
        //head.next의 값을 head에 넣어줘야 하기에 미리 저장
        Node<E> temp_next = head.next;
        //head에 저장된 값을 삭제
        head.next =null;
        head.data = null;
        //head에 head.next값을 넣어준다.
        head = temp_next;
        //size를 감소시켜준다.
        size--;
        //만약 head의 값이 null인 경우에는 list에 남아있는 요소가 없는 것이기 때문에 tail에도 null처리를 해준다.
        if(head==null)
        {
            tail = null;
        }
        //삭제한요소의 값을 반환해준다.
        return data;
    }
    public E remove()//가장 앞에 있는 Node를 제거
    {
        return removeFirst();
    }
    public E remove(int index)//인덱스 위치의 요소를 제거
    {
        //index의 범위가 잘못 된 경우 예외 처리
        if(index<0 || index>=size)
        {
            throw new IndexOutOfBoundsException();
        }
        if(index==0)
        {
            //index의 크기가 0인경우 첫번째 요소를 삭제하는 함수를 호출
            return removeFirst();
        }
        //삭제할 노드 이전 노드
        Node<E> temp_prev = search(index-1);
        //삭제할 노드
        Node<E> temp_del = temp_prev.next;
        //삭제할 노드 이후 노드
        Node<E> temp_next = temp_del.next;
        //삭제할 노드 data 미리 저장
        E data = temp_del.data;
        //삭제할 노드 요소 모두 삭제
        temp_del.next = null;
        temp_del.data = null;
        //삭제할 노드의 이전 노드의 next에 삭제할 노드의 이후 노드 참조
        temp_prev.next = temp_next;
        size--;
        
        return data;
    }
    public boolean remove(E value)//value값이 일치하는 위치의 Node를 제거(중복해서 값이 있는경우에는 가장 앞에 있는 Node를 제거)
    {
        if(size==0)
        {
            //삭제할 노드가 없는경우 예외 처리
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = head;
        for (int i = 0; i < size; i++)
        {
            if (Objects.equals(temp.data, value))
            {
                remove(i);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    public E removeLast()//가장 뒤에 있는 Node를 제거
    {
        return remove(size-1);
    }
}
