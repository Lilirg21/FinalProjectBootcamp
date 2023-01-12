package sophos.domain;

public class Transfer extends Transaction{

    @Override
    public String type() {
        return "Transferencia";
    }

    @Override
    public String execute() {
        String result= this.withDraw(this.getAccount());
        if (result != null) {
            return result;
        }

        result = this.deposit(this.getToAccount());
        if (result!=null) {
            return result;
        }

        return "La transacción ha sido exitosa";
    }

}