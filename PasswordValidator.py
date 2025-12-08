def validator():
    password = input("Input: ")
    confirmPassword = input("Confirm: ")

    print("Length 1: " + str(len(password)))
    print("Length 2: " + str(len(confirmPassword)))

    print("Length Match: "+str(len(password)== len(confirmPassword)))
   # use triple double quotes for comments/ documentation 
    print("Strings Match: "+ str(password == confirmPassword))

# add proper validation (add constraints, no empty string, min len)
# modularize the code, responsibilities
if __name__ == "__main__":
    validator() 

    #have a global exception handler