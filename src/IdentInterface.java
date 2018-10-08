/**
 * @Elements: characters of type Char (int also for numbers?)
 * @Structure: linear
 * @Domain: Identifiers that begin with a letter and have a length of at least 1 character
 *
 * @constructor - Identifier();
 * <dl>
 *     <dr><b>PRE-condition</b></dr>    -
 *     <dt><b>POST-condition</b></dr>   The new Identifier-object is empty
 * </dl>
 **/
//package src;

public interface IdentInterface{

    void initIdent();
    /*
     * @precondition -
     * @postcondition - The Identifier now contains the char "A"
     */

    void add(char c);
    /*
     * @precondition -
     * @postcondition - The char c has been added to the Identifier
     */

    String getIdent();
    /*
     * @precondition -
     * @postcondition - The identifier has been returned
     */

    boolean equals(Identifier identifier2);
    /*
     * @precondition -
     * @postcondition - TRUE: The two identifiers are the same
     *                  FALSE: The two identifiers are not the same
     */

    int size();
    /*
     * @precondition -
     * @postcondition - The size of the identifier has been returned
     */


}
