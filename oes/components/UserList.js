import {React, useState, useEffect} from "react";
import EditUser from "./EditUser";
import User from "./User"

const UserList = ({ user }) => {

    const USER_API_BASE_URL = "http://localhost:8080/api/v1/users/";
    const [users, setusers] = useState(null);
    const [loading, setloading] = useState(true);
    const [userId, setuserId] = useState(null);
    const [responseUser, setResponseUser] = useState(null);

    useEffect(() => {
      const fetchData = async () => {
        setloading(true);
        try{
            const response = await fetch(USER_API_BASE_URL, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            const users = await response.json();
            setusers(users);

        } catch(error) {
            console.log(error);
        }
        setloading(false);
      };
      fetchData();
    }, [user, responseUser]);


    const deleteUser = (e, id) => {
        e.preventDefault();
        fetch(USER_API_BASE_URL + id, {
            method: "DELETE",
        }).then((res) => {
            if(users) {
                setusers((prevElement) => {
                    return prevElement.filter((user) => user.idUser !== id);
                });
            }
        });
    };

    const editUser = (e, id) => {
        e.preventDefault();
        setuserId(id);

    };

  return (
    <>
        <div className="container mx-auto my-8">
            <div className="flex shadow border-b">
                <table className="min-w-full">
                    <thead className="bg-gray-400">
                        <tr>
                            <th className="rounded-tl-md rounded-tr-none rounded-b-none text-left font-medium text-white uppercase tracking-wide py-6 px-6">Логин</th>
                            <th className="text-left rounded-none font-medium text-white uppercase tracking-wide py-6 px-6">Пароль</th> 
                            <th className="text-left rounded-none font-medium text-white uppercase tracking-wide py-6 px-6">Почта</th> 
                            <th className="text-left rounded-none font-medium text-white uppercase tracking-wide py-6 px-6">Баланс</th> 
                            <th className="text-center rounded-none font-medium text-white uppercase tracking-wide py-6 px-6">Статус</th>
                            <th className="text-center rounded-none font-medium text-white uppercase tracking-wide py-6 px-6">Роли</th>
                            <th className="rounded-tr-md rounded-tl-none rounded-b-none text-right font-medium text-white uppercase tracking-wide py-6 px-6">Действия</th>  
                        </tr>
                    </thead>

                    {!loading && (
                    <tbody className="bg-white">
                            {users?.map((user) => (
                                <User user={user} key = {user.idUser} deleteUser={deleteUser} editUser={editUser}/>
                            ))}
                    </tbody>
                    )}
                </table>
            </div>
        </div>

        <EditUser userId={userId} setResponseUser={setResponseUser}/>
    </>
  )
}

export default UserList