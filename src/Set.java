package src;

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
    }

    public boolean find(E l) {
        return wrapperList.find(l);
    }

    public SetInterface<E> difference(SetInterface<E> set2){
        Set differenceSet = new Set();

        return differenceSet;
    }

    public SetInterface<E> intersection(SetInterface<E> set2){

        Set intersectionSet = new Set();

        return intersectionSet;
    }

    public SetInterface<E> union(SetInterface<E> set2){
        Set unionSet = new Set();

        return unionSet;
    }

    public SetInterface<E> symmetricDifference(SetInterface<E> set2){
        Set intersectSet = this.intersection(set2);
        Set symdifSet = new Set();

        for(int j = 0; j<this.size;j++){
            boolean intersectFound = false;
            for(int k = 0; k<intersectSet.size;k++){
                if(this.getIndentValue(j).toString().equals(intersectSet.getIndentValue(k).toString())){
                    intersectFound = true;
                }
            }
            if(!intersectFound){
                Identifier foundIdent = new Identifier(this.getIndentValue(j));
                symdifSet.add(foundIdent);
            }
        }

        for(int l = 0; l<set2.size();l++){
            boolean intersectFound = false;
            for(int m = 0; m<intersectSet.size;m++){
                if(set2.getIndentValue(l).toString().equals(intersectSet.getIndentValue(m).toString())){
                    intersectFound = true;
                }
            }
            if(!intersectFound){
                Identifier foundIdent2 = new Identifier(set2.getIndentValue(l));
                symdifSet.add(foundIdent2);
            }
        }
        return symdifSet;
    }

    public SetInterface<E> copySet(){
        Set<E> copySet = new Set<E>();
        return copySet;
    }
}