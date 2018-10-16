
/*
 * elements: objects of type E
 * structure: linear
 * domain: A row of elements of type E.
 *
 * There is a default constructor that creates an empty object.
 *
 */

public interface SetInterface<E extends Comparable> {
    void initSet();
    /*
     * @precondition -
     * @postcondition - The set has been emptied
     */

    boolean add(E s);
    /*
     * @precondition -
     * @postcondition - TRUE: Element s has been added to the set
     *                  FALSE: Element s has not been added to the set
     */

    boolean remove(int i);
    /*
     * @precondition -
     * @postcondition - TRUE: The element at index i of the set has been removed
     *                  FALSE: The element at index i of the set has not been removed
     */

    boolean isEmpty();
    /*
     * @precondition -
     * @postcondition - TRUE: The set is empty
     *                  FALSE: The set is not empty
     */

    int size();
    /*
     * @precondition -
     * @postcondition - The size of the set has been returned
     */

    boolean find(E l);
    /*
     * @precondition -
     * @postcondition - TRUE: The set contains the Element l
     *                - FALSE: The set does not contain the Element l
     */

    void printSet();
    /*
     * @precondition -
     * @postcondition - The set has been printed as a string.
     *
     */

    SetInterface<E> difference(SetInterface<E> set2);
    /*
     * @precondition -
     * @postcondition - The difference between Set 1 and Set 2 has been calculated and returned
     */

    SetInterface<E> intersection(SetInterface<E> set2);
    /*
     * @precondition -
     * @postcondition - The intersection between Set 1 and Set 2 has been calculated and returned
     */

    SetInterface<E> union(SetInterface<E> set);
    /*
     * @precondition -
     * @postcondition - The union between Set 1 and Set 2 has been calculated and returned
     */

    SetInterface<E> symmetricDifference(SetInterface<E> set2);
    /*
     * @precondition -
     * @postcondition - The symmetric difference between Set 1 and Set 2 has been calculated and returned
     */


    SetInterface<E> copySet();
    /*
     * @precondition -
     * @postcondition - A deep copy of the set has been returned
     */
}