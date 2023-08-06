Feature: Verify Pet api is working as expected

#############################  POSITIVE SCENARIOS

  Scenario Outline: Search for pets by '<status>'
    Given user searches for a list of pets by status '<status>'

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  Scenario Outline: Create a new pet entry
    Given user creates a new pet with categoryId '<categoryId>' categoryName '<categoryName>' tagId '<tagId>' tagName '<tagName>' photoUrls '<photoUrls>' status '<status>'
    Then user verifies the new entry is added

    Examples:
      | categoryId | categoryName | tagId | tagName | photoUrls                                       | status    |
      | 92         | MyCat        | 59    | cat     | http://dummyimage.com/150x100.png/5fa2dd/ffffff | available |
      | 19         | MyDog        | 66    | dog     | http://dummyimage.com/138x100.png/cc0000/ffffff | available |
      | 64         | MyCat        | 69    | cat     | http://dummyimage.com/217x100.png/ff4444/ffffff | available |

  Scenario: Delete an already existing entry
    Given user searches for an entry
    Then user deletes the entry



#############################  NEGATIVE SCENARIOS





  Scenario: Access invalid resource
    Given user tries to access an invalid resource

  Scenario: Wrong HTTP method
    Given user tries to use an invalid method to access resource