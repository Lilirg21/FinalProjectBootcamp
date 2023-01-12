package sophos.domain;

public class Withdraw extends Transaction{
    @Override
    public String type() {
        return "Retiro";
    }

    @Override
    public String execute() {
        return  this.withDraw(this.getAccount());
    }
}