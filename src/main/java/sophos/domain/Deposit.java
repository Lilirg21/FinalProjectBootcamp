package sophos.domain;

public class Deposit extends Transaction{
    @Override
    public String type() {
        return "Consignación";
    }

    @Override
    public String execute() {
        boolean success = this.deposit(this.getAccount());
        if (!success) {
            return "Error: La transacción no se ha podido completar";
        }
        return "Transacción exitosa";
    }
}
