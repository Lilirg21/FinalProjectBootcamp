package sophos.domain;

public class Withdraw extends Transaction{
    @Override
    public String type() {
        return "Retiro";
    }

    @Override
    public String execute() {
        boolean success = this.withDraw(this.getAccount());
        if (!success) {
            return "Error: La transacción no se ha podido completar";
        }
        return "Transacción exitosa";
    }
}