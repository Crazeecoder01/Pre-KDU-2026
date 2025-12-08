def genre_preference():
    my_list = []

    for i in range(10):
        item = input(f"Genre {i+1}: ")
        my_list.append(item)

    print("List: " + str(my_list))
    
    my_set = set(my_list)
    print("Set: " + str(my_set))

    my_dict = {}
    for genre in my_list:
        if genre in my_dict:
            my_dict[genre] += 1
        else:
            my_dict[genre] = 1

    print("Dictionary: " + str(my_dict))

    

if __name__ == "__main__":
    genre_preference()