import Head from 'next/head'
import AddUser from '../components/AddUser'
import Navbar from '../components/Navbar'

export default function Home() {
  return (
      <div className=" bg-gradient-to-bl from-fuchsia-800 to-blue-700 h-screen">
        <Head>
          <title>OES</title>
        </Head>

        <Navbar />
        <main>
          <AddUser />
        </main>

      </div>
  )
}
