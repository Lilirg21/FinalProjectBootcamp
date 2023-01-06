package sophos.domain;

public class Transfer extends Transaction{

    @Override
    public String type() {
        return "Transferencia";
    }

    @Override
    public String execute() {
        boolean success = this.withDraw(this.getAccount());
        if (!success) {
            return "Error: La transacción no se ha podido completar";
        }

        success = this.deposit(this.getToAccount());
        if (!success) {
            return "Error: La transacción no se ha podido completar";
        }

        return "La transacción ha sido exitosa";
    }

}