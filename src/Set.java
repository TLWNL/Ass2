package src;

import java.lang.reflect.Array;
import java.util.List;

public class Set<E extends Comparable> implements SetInterface<E> {
    private int size;
    private int listIndex;
    private List<E> wrapperList;

    public Set(){
        Set<E> s = new Set<E>();
    }

    public void initSet() {
        this.wrapperList = new List;
        listIndex = 0;
        size = 0;
    }

    public boolean add(SetInterface<E> s) {
        wrapperList.add(s.getWL().retrieve());
        return false;
    }

    public boolean remove(int i){
        if(isEmpty())
            return false;
        else
            do {
                if (listIndex < i)

            }(while(listIndex != i))

            wrapperList.remove()
        return true;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }

    public void printSet(){

    }

    public boolean contains(SetInterface<E> s) {
        if(l == this.set)
            return true;
        else
            return false;
    }

    public List<E> getWL(){
        return this.wrapperList;
    }

    public SetInterface<E> difference(SetInterface<E> set2){
        Set differenceSet = new Set();

        boolean intersectFound;

        for(int i = 0; i<this.set.;i++){
            intersectFound = false;
            for(int j = 0 ; j<set2.size(); j++){
                if((this.getIndentValue(i).toString()).equals((set2.getIndentValue(j).toString()))){
                    intersectFound = true;
                }
            }
            if(!intersectFound){
                Identifier A = new Identifier(getIndentValue(i));
                differenceSet.add(A);
            }
        }

        for(int k = 0; k<set2.size;k++){
            intersectFound = false;
            for(int l = 0 ; l<this.size(); l++){
                if((this.getIndentValue(l).toString()).equals((set2.getIndentValue(k).toString()))){
                    intersectFound = true;
                }
            }
            if(!intersectFound){
                Identifier A = new Identifier(set2.getIndentValue(k));
                differenceSet.add(A);
            }
        }*/
        return differenceSet;
    }

    public SetInterface<E> intersection(SetInterface<E> set2){

        Set intersectionSet = new Set();
        for(int i = 0; i<this.size;i++){
            for(int j = 0; j<set2.size();j++){
                if((this.getIndentValue(i).toString()).equals((set2.getIndentValue(j).toString()))){
                    Identifier A = new Identifier(this.getIndentValue(i));
                    intersectionSet.add(A);
                }
            }
        }
        return intersectionSet;
    }

    public SetInterface<E> union(SetInterface<E> set2){
        Set unionSet = new Set();
        boolean intersectFound;
        for(int i = 0; i<this.size;i++){
            intersectFound = false;
            for(int j = 0 ; j<set2.size(); j++){
                if((this.getIndentValue(i).toString()).equals((set2.getIndentValue(j).toString()))){
                    intersectFound = true;
                }
            }
            if(!intersectFound) {
                Identifier identFound = new Identifier(this.getIndentValue(i));
                unionSet.add(identFound);
            }
        }

        for(int k=0;k<set2.size();k++){
            Identifier identFound2 = new Identifier(set2.getIndentValue(k));
            unionSet.add(identFound2);
        }
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
}*/