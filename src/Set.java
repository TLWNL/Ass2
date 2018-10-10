package src;

public class Set<E extends Comparable<E>> implements SetInterface<E> {
    private int size;
    private int listIndex;
    private List<E> wrapperList;

    public Set(){
        this.wrapperList = new List<>();
        this.initSet();
    }

    public void initSet() {
        this.wrapperList.init();
        this.listIndex = 0;
        this.size = 0;
    }

    public boolean add(E l) {
        if(!this.find(l)) {
            this.wrapperList.insert(l);
            //System.out.printf("Insert complete, inserted element: %s\n", this.wrapperList.retrieve());
            size++;
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
                size--;
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
        System.out.printf("Size = %s \n", this.size);
        this.wrapperList.goToFirst();
        for(int i = 0; i<size; i++){
            toPrint.append(this.wrapperList.retrieve());
            this.wrapperList.goToNext();
            if(i != size-1)
                toPrint.append(" ");
        }
        System.out.printf("{%s}\n", toPrint);

    }


    public boolean find(E l) {
        for(int i = 0; i<size; i++){
            if(this.wrapperList.retrieve().compareTo(l) == 0){
                return true;
            }
        }
        return false;
    }

    public SetInterface<E> difference(SetInterface<E> set2){
        Set<E> differenceSet = new Set<>();
        while(this.wrapperList.goToNext()){
            if(!set2.find(this.wrapperList.retrieve()))
                differenceSet.add(this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }
        return differenceSet;
    }

    public SetInterface<E> intersection(SetInterface<E> set2){
        Set intersectionSet = new Set<>();
        this.wrapperList.goToFirst();
        while(this.wrapperList.goToNext()){
            if(set2.find(this.wrapperList.retrieve()))
                intersectionSet.add(this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }

        return intersectionSet;
    }

    public SetInterface<E> union(SetInterface<E> set2){
        Set<E> unionSet = new Set<>();
        this.wrapperList.goToFirst();

        unionSet.copyElements((Set<E>)set2);        // Adds the entire second set
        System.out.printf("UNION SIZE SET = %s", unionSet.size);
        unionSet.printSet();

        while(this.wrapperList.goToNext()){
            if(unionSet.find(this.wrapperList.retrieve()))    // Duplicate element, so go to next
                this.wrapperList.goToNext();
            else{
                unionSet.add(this.wrapperList.retrieve());
                this.wrapperList.goToNext();
            }
        }
        return unionSet;
    }

    public SetInterface<E> symmetricDifference(SetInterface<E> set2){
        //All elements in both sets that are not contained in the intersection
        Set<E> intersectSet = (Set<E>)this.intersection(set2);
        Set<E> symdifSet = new Set<>();
        Set<E> tmpSet = new Set<>();
        tmpSet.copyElements((Set<E>)set2);

        while(this.wrapperList.goToNext()){
            if(intersectSet.find(this.wrapperList.retrieve())){
                this.wrapperList.goToNext();
            }
            else{
                symdifSet.add(this.wrapperList.retrieve());
            }
        }
        while(tmpSet.wrapperList.goToNext()){
            if(intersectSet.find(tmpSet.wrapperList.retrieve())){
                tmpSet.wrapperList.goToNext();
            }
            else{
                symdifSet.add(tmpSet.wrapperList.retrieve());
            }
        }
        return symdifSet;
    }

    public SetInterface<E> copySet(){
        Set<E> copySet = new Set<>();
        this.wrapperList.goToFirst();
        while(this.wrapperList.goToNext()){
            copySet.add( this.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }
        return copySet;
    }

    public Set<E> copyElements(Set<E> setInput){
        this.wrapperList.goToFirst();
        System.out.printf("SIZE OF SET TO BE COPIED= %s\n", setInput.size());
        for(int i = 0; i<setInput.size; i++){
            this.add(setInput.wrapperList.retrieve());
            this.wrapperList.goToNext();
        }
        return this;
    }

}