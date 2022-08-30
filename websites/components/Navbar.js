import NavItem from "./NavItem";

const MENU_LIST = [
    { text: "Index", href: "/" },
    { text: "Match Key Provider", href: "/matchkeyprovider" },
    { text: "Publisher", href: "/pub" },
    { text: "Advertiser", href: "/adv" },
    { text: "Adtech", href: "/adtech" },
];
const Navbar = () => {
    return (
        <header>
            <nav className={`nav`}>
                <div className={"nav__menu-list"}>
                    {MENU_LIST.map((menu) => {
                        return (
                            <div key={menu.text}>
                                <NavItem {...menu} />
                            </div>
                        )
                    })}
                </div>
            </nav>
        </header>
    );
};

export default Navbar;