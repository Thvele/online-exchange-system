import React from 'react'
import { Fragment, useState, useEffect } from "react"
import { Dialog, Transition } from "@headlessui/react"

const EditUser = ({ userId, setResponseUser }) => {
    const USER_API_BASE_URL = "http://localhost:8080/api/v1/users/";
    const [isOpen, setisOpen] = useState(false);

    const [user, setUser] = useState({
        idUser: "",
        login: "",
        password: "",
        email: "",
        balance: "",
        active: "",
    });

    useEffect(() => {
        const fetchData = async () => {
            try{
                const response = await fetch(USER_API_BASE_URL + userId, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });

                const _user = await response.json();
                setUser(_user);
                setisOpen(true);

            }
            catch(error) {}
        };        

        if(userId){
            fetchData();
        }

    }, [userId]);
    

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

    const handleChangeCombobox = (event) => {
        const value = event.target.checked;
        setUser({...user, [event.target.name]: value});
    };

    const reset = (e) => {
        e.preventDefault;
        setisOpen(false);
    };

    const  updateUser = async (e) => {
        e.preventDefault();
        const response = await fetch(USER_API_BASE_URL + userId, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });

        if(!response.ok) {
            document.getElementById("label-error").textContent = "Проверьте правильность заполнения данных!";
        }
        else{
            const _user = await response.json();
            setResponseUser(_user);
            reset(e);
        }
    };

    
  return (
    <Transition appear show={isOpen} as={Fragment}>
            <Dialog as="div" className="fixed inset-0 z-10 overflow-y-auto" onClose={closeModal}>
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

                                <Dialog.Title as="h3" className="text-lg font-medium leading-6 text-gray-900">Изменить пользователя</Dialog.Title>
                                <div className="my-1">
                                    <label id="label-error" className="block text-red-700 text-center text-sm font-normal"></label>
                                </div>
                                <div className="flex max-w-md max-auto">
                                    <div className="py-2 min-w-full">
                                        <form onSubmit={updateUser}>
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

                                                <div className="form-check mt-4 mb-1">
                                                    <p>
                                                        <label>
                                                            <input id="checkbox-active" checked={user.active} onChange={(e) => handleChangeCombobox(e)} type="checkbox" name="active"/>
                                                            <span className="hover:cursor-progress text-black">Активирован</span>
                                                        </label>
                                                    </p>
                                                </div>

                                                <div className="text-right">
                                                    <button type="submit" className="rounded text-white bg-green-600 hover:bg-green-500 py-2 px-10 mt-5">Изменить</button>
                                                </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                    </Transition.Child>
                </div>
            </Dialog>
        </Transition>
  )
}

export default EditUser