db.createUser(
    {
      user: "merch",
      pwd: "merch",
      roles: [
        {
          role: "readWrite",
          db: "equipment"
        }
      ]
    }
);