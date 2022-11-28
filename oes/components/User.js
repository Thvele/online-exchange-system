import 'materialize-css/dist/css/materialize.min.css'

const User = ({ user, deleteUser, editUser }) => {
    
  return (
    <tr key = {user.idUser}>
        <td className="text-left px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                {user.login}
            </div>
        </td>
        <td className="text-left px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                {user.password}
            </div>
        </td>
        <td className="text-left px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                {user.email}
            </div>
        </td>
        <td className="text-left px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                {user.balance} руб.
            </div>
        </td>
        <td className="text-center px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                <div className="form-check">
                    <input  className="form-check-input w-48 h-4 bg-green-600 rounded-sm" type="checkbox" checked={true} readOnly={true}/>
                </div>
            </div>
            <div className="text-sm text-gray-500">
                <p>
                    <label>
                        <input type="checkbox" checked={user.active} readOnly={true}/>
                        <span className="hover:cursor-progress text-black">Активирован</span>
                    </label>
                </p>
            </div>
        </td>
        <td className="text-left px-6 py-4 whitespace-nowrap rounded-none">
            <div className="text-sm text-gray-500">
                {user.roles}
            </div>
        </td>
        <td className="text-right px-6 py-4 whitespace-nowrap rounded-none">
            <a onClick={(e, id) => editUser(e, user.idUser)} className="text-black hover:text-indigo-800 hover:cursor-pointer font-bold px-5">Изменить</a>
            <a onClick={(e, id) => deleteUser(e, user.idUser)} className="text-black hover:text-red-800 hover:cursor-pointer font-bold">Удалить</a>
        </td>
    </tr>
  )
}

export default User