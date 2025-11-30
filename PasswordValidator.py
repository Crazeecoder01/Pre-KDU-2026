def validator():
    password = input("Input: ")
    confirmPassword = input("Confirm: ")

    print("Length 1: " + str(len(password)))
    print("Length 2: " + str(len(confirmPassword)))

    print("Length Match: "+str(len(password)== len(confirmPassword)))
    
    print("Strings Match: "+ str(password == confirmPassword))



if __name__ == "__main__":
    validator() 