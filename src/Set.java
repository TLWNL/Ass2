package src;

import java.lang.reflect.Array;
import java.util.List;

public class Set<E extends Comparable> implements SetInterface<E> {
    private int size;
    private E[] set;

    public Set(){
        Set<E> s = new Set<E>();
    }

    public void initSet() {             // Should I reset the Set to a completely new Set?
        this.set = null;
        size = 0;
    }

    public boolean add(Set<E> s) {
        set = (E[])Array.newInstance(s, 1);

        return false;
    }

    public boolean remove(){
        if(isEmpty())
            return false;
        else
            this.set = null;
        return true;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    // Is size still needed, as there is a single row of elements in the Set?
    public int size(){
        return size;
    }

    public boolean contains(E l) {
        if(l == this.set)
            return true;
        else
            return false;
    }

    public Set difference(Set set2){
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

    public Set intersection(Set set2){

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

    public Set union(Set set2){
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

    public Set symmetricDifference(Set set2){
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

    public Set<E> copySet(){}
}*/