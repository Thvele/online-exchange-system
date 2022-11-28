import { Dialog, Transition } from "@headlessui/react"
import { Fragment, useState } from "react"
import React from "react"
import UserList from '../components/UserList'
const AddUser = () => {
    const USER_API_BASE_URL = "http://localhost:8080/api/v1/users/";
    const [isOpen, setisOpen] = useState(false);

    const [user, setUser] = useState({
        idUser: "",
        login: "",
        password: "",
        email: "",
        balance: "",
        active: true,
        roles: [""],
    });
    const [responseUser, setResponseUser] = useState({
        idUser: "",
        login: "",
        password: "",
        email: "",
        balance: "",
        active: true,
        roles: [""],
      });

    function closeModal() {
        setisOpen(false);
    };

    function openModal() {
        setisOpen(true);
    };

    const handleChange = (event) => {
        const value = event.target.value;
        setUser({...user, [event.target.name]: value});
    };

    const saveUser = async (e) => {
        e.preventDefault();
        const response = await fetch(USER_API_BASE_URL, {
            method: "POST",
            headers:{
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });
        if(!response.ok) {
            document.getElementById("label-error").textContent = "Проверьте правильность заполнения полей!";
        }
        else{
            const _user = await response.json();
            setResponseUser(_user);
            reset(e);
        }
    };

    const reset = (e) => {
        e.preventDefault;
        setUser({
            idUser : "",
            login : "",
            password : "",
            email : "",
            balance : "",
            active : true,
            roles: [""],
        });
        setisOpen(false);
    };

  return (
    <>
        <div className="container mx-auto my-8">
            <div className="h-12 mb-3">
                <a onClick={openModal} className="waves-effect bg-gray-500 text-white rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-blue-500 focus:outline-none focus:shadow-outline">Добавить пользователя</a>
            </div>
        </div>

        <Transition appear show={isOpen} as={Fragment}>
            <Dialog as="div" className="fixed inset-0 z-10 overflow-y-auto"
            onClose={closeModal}>
                <div className="min-h-screen px-4 text-center">
                    <Transition.Child
                        as={Fragment}
                        enter="ease-out duration-300"
                        enterFrom="opacity-0"
                        enterTo="opacity-100"
                        leave="ease-in duration-200"
                        leaveFrom="opacity-100"
                        leaveTo="opacity-0" >
                            <div className="inline-block w-full max-w-md p-6 my-8 overflow-hidden text-left align-middle transition-all transform shadow-lg bg-white rounded-md">
                                <div className="float-right">
                                    <a onClick={reset} className="rounded text-black hover:text-amber-900 w-5 h-5 align-top border-none hover:cursor-pointer">X</a>
                                </div>

                                <Dialog.Title as="h3" className="text-lg font-medium leading-6 text-gray-900">Добавить нового пользователя</Dialog.Title>
                                <div className="my-1">
                                    <label id="label-error" className="block text-red-700 text-center text-sm font-normal"></label>
                                </div>
                                    <div className="flex max-w-md max-auto">
                                        <div className="py-2 min-w-full">
                                            <form onSubmit={saveUser}>
                                                <div className="h-14 mb-5">
                                                    <label className="block text-gray-500 text-sm font-normal">Логин</label>
                                                    <input value={user.login} onChange={(e) => handleChange(e)} type="text" placeholder="Логин" minLength="4" maxLength="100" name="login" id="input-login" className="h-10 w-96 border mt-2 px-2 py-2 validate" required></input>
                                                </div>
                                                <div className="ml-5">
                                                    <ul className="text-orange-500 text-sm font-normal">
                                                        <li>• От 4 до 100 символов</li>
                                                    </ul>
                                                </div>

                                                <div className="h-14 my-4">
                                                    <label className="block text-gray-500 text-sm font-normal">Пароль</label>
                                                    <input value={user.password} onChange={(e) => handleChange(e)} type="text" placeholder="Пароль" minLength="8" name="password" id="input-password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!?]).*" className="h-10 w-96 border mt-2 px-2 py-2" required></input>
                                                </div>
                                                <div className="ml-5">
                                                    <ul className="text-orange-500 text-sm font-normal">
                                                        <li>• От 8 символов</li>
                                                        <li>• Только латиница</li>
                                                        <li>• Должна быть хотя бы одна цифра</li>
                                                        <li>• Должна быть хотя бы одна строчная буква</li>
                                                        <li>• Должна быть хотя бы одна заглавная буква</li>
                                                        <li>• Должен быть хотя бы один специальный символ (@#$%^&+=!?)</li>
                                                        <li>• Нельзя использовать пробелы</li>
                                                    </ul>
                                                </div>

                                                <div className="h-14 my-4">
                                                    <label className="block text-gray-500 text-sm font-normal">Почта</label>
                                                    <input value={user.email} onChange={(e) => handleChange(e)} type="email" placeholder="example@ex.com" name="email" id="input-email" minLength="1" className="h-10 w-96 border mt-2 px-2 py-2" required autocomplete="off"></input>
                                                </div>


                                                <div className="h-14 my-4">
                                                    <label className="block text-gray-500 text-sm font-normal">Баланс</label>
                                                    <input value={user.balance} onChange={(e) => handleChange(e)} type="number" placeholder="123.45" name="balance" id="input-balance" min="0" max="999999.99" step="0.01" className="h-10 w-96 border mt-2 px-2 py-2" required></input>
                                                </div>
                                                <div className="ml-5">
                                                    <ul className="text-orange-500 text-sm font-normal">
                                                        <li>• От 0 до 999999.99</li>
                                                    </ul>
                                                </div>

                                                <div className="text-right">
                                                    <button type="submit" className="rounded text-white bg-green-600 hover:bg-green-500 py-2 px-10 mt-5">Добавить</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                            </div>
                    </Transition.Child>
                </div>
            </Dialog>
        </Transition>

        <UserList user={responseUser}/>
    </>
  );
};

export default AddUser;