public enum State {
    Variable1,
    Sign,
    Variable2;

    @Override
    public String toString() {
        return super.toString();
    }
    protected State setNext(){
        State stateToReturn;
        switch (this){
            case Variable1 -> stateToReturn = State.Sign;
            case Sign -> stateToReturn = State.Variable2;
            default -> stateToReturn = State.Variable1;
        }
        return stateToReturn;
    }
}
