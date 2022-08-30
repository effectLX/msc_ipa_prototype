import Link from "next/link";
const NavItem = ({ text, href }) => {
    return (
        <Link href={href}>
            <a className={`nav__link`}>{text}</a>
        </Link>
    );
};

export default NavItem;