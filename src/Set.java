package src;

public class Set<E extends Comparable> implements SetInterface {
    //private static final int MAX_NUM_OF_ELEMENTS = 10;
    //private int size;
    //private E set;
    // MUY IMPORTANTE! Is Set checked or unchecked?! I hereby assume that it is checked.
    // This intuition is based on the fact that our calculator ONLY does operations on BigInts. So we should
    // def check if there is for example a string input, as that should be rejected.
    public Set(){
    //Class<E> set as param
    }

    public Set(Set src) {

    }

    public boolean contains(Identifier src) {

        return false;
    }

    public void initSet() {

    }

    public void add(Identifier a) {

    }

    public int size(){

    }

    public boolean isEmpty(){
        return false;
    }

    public boolean remove(int i){
        return false;
    }

//    public E get() {

  //  }


    public void printSet(){

    }

    public Set difference(Set set2){

        Set differenceSet = new Set();
        /*
        boolean intersectFound;

        for(int i = 0; i<this.size;i++){
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
}*/