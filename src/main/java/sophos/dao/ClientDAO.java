package sophos.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import java.sql.Statement;

import sophos.domain.Client;
import sophos.infraestructure.MySQL;
import sophos.repositories.ClientRepository;

@Repository
public class ClientDAO implements ClientRepository {

	private MySQL source = new MySQL();
	
	@Override
	public boolean save(Client client) {
        try {
            String strQuery = "INSERT INTO clients(documentType, document, name, lastName, email, dateOfBirth, createdOn, createdBy, updatedOn, updatedBy)";
            strQuery += "VALUES (?, ?, ?, ?, ?, ?, NOW(), \"admin\", NOW(), \"admin\")";
            
            java.sql.Date sqlDate = null;
            if (client.getDateOfBirth() != null) {
                sqlDate = new Date(client.getDateOfBirth().getTime());
            }

            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getDocumentType());
            stmt.setString(2, client.getDocument());
            stmt.setString(3, client.getName());
            stmt.setString(4, client.getLastName());
            stmt.setString(5, client.getEmail());
            stmt.setDate(6, sqlDate);

            stmt.execute();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
            	client.withId(generatedKeys.getInt(1));
            }

            this.source.CloseConnection();

        } catch(SQLException e) {
            System.out.println("ClientDAO / save / SQLException: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
        	System.out.println("ClientDAO / save / ClassNotFoundException: " + e.getMessage());
            return false;
		}

        return true;
	}

	@Override
	public boolean update(Client client) {
		try {
            String strQuery = "UPDATE clients SET documentType=?,document=?,name=?,lastName=?,email=?,dateOfBirth=?,status=?,updatedOn=NOW(),updatedBy='Admin' WHERE id = ?";
            
            java.sql.Date sqlDate = null;
            if (client.getDateOfBirth() != null) {
                sqlDate = new Date(client.getDateOfBirth().getTime());
            }


            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setString(1, client.getDocumentType());
            stmt.setString(2, client.getDocument());
            stmt.setString(3, client.getName());
            stmt.setString(4, client.getLastName());
            stmt.setString(5, client.getEmail());
            stmt.setDate(6, sqlDate);
            stmt.setBoolean(7, client.isStatus());
            stmt.setInt(8, client.getId());

            stmt.execute();
            

            this.source.CloseConnection();

        } catch(SQLException e) {
            System.out.println("ClientDAO / update / SQLException: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
        	System.out.println("ClientDAO / update / ClassNotFoundException: " + e.getMessage());
            return false;
		}

        return true;

	}

	@Override
	public boolean delete(int id) {
        try {
            String strQuery = "DELETE FROM clients WHERE id = ?";            
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, id);
            stmt.execute();  
            this.source.CloseConnection();

        } catch(SQLException e) {
            System.out.println("ClientDAO / delete / SQLException: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
        	System.out.println("ClientDAO / delete / ClassNotFoundException: " + e.getMessage());
            return false;
		}

        return true;
	}

	@Override
	public Client findById(int id) {
        Client client = new Client();
        String strQuery = "SELECT id, documentType, document, name, lastName, email, dateOfBirth, status FROM clients WHERE id = ?";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.withId(rs.getInt("id"));
                client.withDocumentType(rs.getString("documentType"));
                client.withDocument(rs.getString("document"));
                client.withName(rs.getString("name"));
                client.withLastName(rs.getString("lastName"));
                client.withEmail(rs.getString("email"));
                client.withDateOfBirth(rs.getDate("dateOfBirth"));
                client.withStatus(rs.getBoolean("status"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return client;
	}

	@Override
	public ArrayList<Client> all() {
        ArrayList<Client> clients = new ArrayList<Client>();
        String strQuery = "SELECT id, documentType, document, name, lastName, email, dateOfBirth, status, createdOn, createdBy, updatedOn, updatedBy FROM clients";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.withId(rs.getInt("id"));
                client.withDocumentType(rs.getString("documentType"));
                client.withDocument(rs.getString("document"));
                client.withName(rs.getString("name"));
                client.withLastName(rs.getString("lastName"));
                client.withEmail(rs.getString("email"));
                client.withDateOfBirth(rs.getDate("dateOfBirth"));
                client.withCreatedOn(rs.getString("createdOn"));
                client.withCreatedBy(rs.getString("createdBy"));
                client.withUpdatedOn(rs.getString("updatedOn"));
                client.withUpdatedBy(rs.getString("updatedBy"));
                client.withStatus(rs.getBoolean("status"));

                clients.add(client);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return clients;
	}

	@Override
	public Client findByDocument(String documentType, String document) {
        Client client = new Client();
        String strQuery = "SELECT id, documentType, document, name, lastName, email, dateOfBirth, status, createdOn, createdBy, updatedOn, updatedBy FROM clients WHERE documentType = ? AND document = ?";
        try {        	
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setString(1, documentType);
            stmt.setString(2, document);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.withId(rs.getInt("id"));
                client.withDocumentType(rs.getString("documentType"));
                client.withDocument(rs.getString("document"));
                client.withName(rs.getString("name"));
                client.withLastName(rs.getString("lastName"));
                client.withEmail(rs.getString("email"));
                client.withDateOfBirth(rs.getDate("dateOfBirth"));
                client.withCreatedOn(rs.getString("createdOn"));
                client.withCreatedBy(rs.getString("createdBy"));
                client.withUpdatedOn(rs.getString("updatedOn"));
                client.withUpdatedBy(rs.getString("updatedBy"));
                client.withStatus(rs.getBoolean("status"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return client;
	}

	public boolean hasGMF(Client client) {
        String strQuery = "SELECT 1 FROM products WHERE client_id = ? and isGMF = 1";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, client.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;		
	}
	
	public boolean hasProductStatusActiveOrInactive(int idClient) {
        String strQuery = "SELECT DISTINCT 1 FROM products WHERE client_id = ? AND (state = 'ACTIVA' OR state = 'INACTIVA')";
        try {
            PreparedStatement stmt = this.source.conn().prepareStatement(strQuery);
            stmt.setInt(1, idClient);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return false;			
	}
}
