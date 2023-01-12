package sophos.domain;

public class Deposit extends Transaction{
    @Override
    public String type() {
        return "Consignación";
    }

    @Override
    public String execute() {
        return this.deposit(this.getAccount());
    }
}
