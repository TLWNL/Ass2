public class Identifier implements IdentInterface {
    private StringBuffer sb = new StringBuffer();
    public Identifier(){
        StringBuffer ident = new StringBuffer("A");
    }

    public Identifier(Identifier src){
        StringBuffer foundIdentString = new StringBuffer(this.sb);
        Identifier foundIdent = new Identifier();
        for(int i = 0; i< foundIdentString.length(); i++){
            foundIdent.add(foundIdentString.charAt(i));
        }
    }

    public Identifier(StringBuffer sbr){
        sb = new StringBuffer(sbr);
    }

    public void initIdent(){
        sb.append("A");
    }

    public void add(char c){
        sb.append(c);
    }

    public String getIdent(){
        return 	sb.toString();
    }

    public int size(){
        return sb.length();
    }

    public boolean equals(Identifier identifier2){
        if(this.sb.toString().equals(identifier2.sb.toString()))
            return true;
        else
            return false;
    }
}

