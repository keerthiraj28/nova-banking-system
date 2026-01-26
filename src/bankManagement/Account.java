package bankManagement;

import java.util.Date;

public class Account {

    // Personal details
    private String name;
    private Date dob;
    private int age;
    private String mobileNumber;
    private String email;
    private String address;
    private String aadhaarNumber;
    private String panNumber;
    
    // Guardian details
    private String guardianName;
    private String guardianMobileNumber;
    private String guardianEmail;
    private String guardianPanNumber;

    // Account details
    private double balanceAmount;
    private String accountType;
    private String accountNumber;

    // Card details
    private String debitCardNumber;
    private DebitCardStatus debitCardStatus;
    private String cardType;
    private String cardFeature;

    // Nominee details
    private String nomineeName;
    private Integer nomineeAge;
    private String nomineeRelationship;
    private String nomineeMobileNumber;
    private String nomineePanNumber;
    
    // Login details
    private String username;
    private String password;
    
    // Minor validation
    public boolean isMinor() {
        return age < 10;
    }


    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email=email;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianMobileNumber() {
		return guardianMobileNumber;
	}

	public void setGuardianMobileNumber(String guardianMobileNumber) {
		this.guardianMobileNumber = guardianMobileNumber;
	}

	public String getGuardianEmail() {
		return guardianEmail;
	}

	public void setGuardianEmail(String guardianEmail) {
		this.guardianEmail = guardianEmail;
	}

	public String getGuardianPanNumber() {
		return guardianPanNumber;
	}

	public void setGuardianPanNumber(String guardianPanNumber) {
		this.guardianPanNumber = guardianPanNumber;
	}

	public double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
    	this.accountNumber=accountNumber;
    }
    
    public String getDebitCardNumber() {
        return debitCardNumber;
    }
    
    public void setDebitCardNumber(String debitCardNumber) {
    	this.debitCardNumber=debitCardNumber;
    }

    public DebitCardStatus getDebitCardStatus() {
        return debitCardStatus == null ? DebitCardStatus.NONE : debitCardStatus;
    }


    public void setDebitCardStatus(DebitCardStatus debitCardStatus) {
        this.debitCardStatus = debitCardStatus;
    }

    public String getCardType() {
    	return cardType;
    }
    
    public void setCardType(String cardType) {
    	this.cardType=cardType;
    }
    
    public String getCardFeature() {
    	return cardFeature;
    }
    
    public void setCardFeature(String cardFeature) {
    	this.cardFeature=cardFeature;
    }
    
    public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public Integer getNomineeAge() {
		return nomineeAge;
	}

	public void setNomineeAge(Integer nomineeAge) {
		this.nomineeAge = nomineeAge;
	}

	public String getNomineeRelationship() {
		return nomineeRelationship;
	}

	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
	}

	public String getNomineeMobileNumber() {
		return nomineeMobileNumber;
	}

	public void setNomineeMobileNumber(String nomineeMobileNumber) {
		this.nomineeMobileNumber = nomineeMobileNumber;
	}

	public String getNomineePanNumber() {
		return nomineePanNumber;
	}

	public void setNomineePanNumber(String nomineePanNumber) {
		this.nomineePanNumber = nomineePanNumber;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
