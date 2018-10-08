//package src;

public class Set<E extends Comparable> implements SetInterface<E> {
    private int size;
    private int listIndex;
    private List wrapperList;

    public Set(){
        Set<E> s = new Set<E>();
    }

    public void initSet() {
        this.wrapperList = new List();
        listIndex = 0;
        size = 0;
    }

    public boolean add(E l) {
        if(!this.find(l)) {
            wrapperList.insert(l);
            return true;
        }
        return false;
    }

    public boolean remove(int i){
        if(isEmpty())
            return false;
        else {
            wrapperList.goToFirst();
            listIndex = 0;
            while(listIndex != i){
                wrapperList.goToNext();
                listIndex++;
                wrapperList.remove();
            }
            wrapperList.goToFirst();
            listIndex = 0;
            return true;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printSet(){
        StringBuffer toPrint = new StringBuffer();
        wrapperList.goToFirst();
        while(wrapperList.goToNext()){
            toPrint.append(wrapperList.retrieve().toString());
        }
        System.out.printf("{%s}\n", toPrint);

    }

    public List getList(){
        return this.wrapperList;
    }

    public boolean find(E l) {
        return wrapperList.find(l);
    }

    public SetInterface<E> difference(SetInterface<E> set2){
        Set<E> differenceSet = new Set<>();
        Set<E> copiedSet = new Set<>();
        while(this.wrapperList.goToNext()){
            if(!copiedSet.find((E) this.wrapperList.retrieve()))
                differenceSet.add((E) this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }

        return differenceSet;
    }

    public SetInterface<E> intersection(SetInterface<E> set2){

        Set intersectionSet = new Set<>();
        Set<E> copiedSet = (Set<E>) set2.copySet();
        this.wrapperList.goToFirst();
        while(this.wrapperList.goToNext()){
            if(copiedSet.find((E) this.wrapperList.retrieve()))
                intersectionSet.add(this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }

        return intersectionSet;
    }

    public SetInterface<E> union(SetInterface<E> set2){
        Set<E> unionSet = new Set<>();
        Set<E> copiedSet = (Set<E>) set2.copySet();
        //copiedSet.wrapperList = set2.copySet().getList();       // This might be redundant
        this.wrapperList.goToFirst();
        while(this.wrapperList.goToNext()){                     // Adds the entire first set
            unionSet.add((E)this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }
        copiedSet.wrapperList.goToFirst();
        while(copiedSet.wrapperList.goToNext()){
            if(this.wrapperList.find(copiedSet.wrapperList.retrieve()))
            {
                copiedSet.wrapperList.goToNext();
            }
            else{
                unionSet.add((E) copiedSet.wrapperList.retrieve());
                copiedSet.wrapperList.goToNext();
            }
        }
        return unionSet;
    }

    public SetInterface<E> symmetricDifference(SetInterface<E> set2){
        Set<E> symdifSet = new Set<>();

        return symdifSet;
    }

    public SetInterface<E> copySet(){
        Set<E> copySet = new Set<E>();
        this.wrapperList.goToFirst();
        while(this.wrapperList.goToNext()){
            copySet.add((E) this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }
        return copySet;
    }
}